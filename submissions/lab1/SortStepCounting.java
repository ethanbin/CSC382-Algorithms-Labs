// Written by Ethan Binyaminov
package lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static lab1.SortTimeCounting.*;

public class SortStepCounting {
    static double stepCount = 0;

    private static void merge(int[] arr, int start, int mid, int end){
        int leftSize = mid - start + 1;
        stepCount++;
        int rightSize = end - mid;
        stepCount++;

        // temp arrays to contain 2 subarrays to merge
        int[] left = new int[leftSize];
        stepCount++;
        int[] right = new int[rightSize];
        stepCount++;

        // copy into left and right their respective subarrays from arr.
        // increment step count for assigning i.
        stepCount++;
        // leftSize many comparisons will be made, and i will be incremented
        // the same number of times, so add 2 * leftSize number of steps.
        stepCount+= 2*leftSize;
        for (int i = 0; i < leftSize; i++) left[i] = arr[i + start];
        // adding to stepCount for same reason as above
        stepCount++;
        stepCount+= 2*rightSize;
        for (int i = 0; i < rightSize; i++) right[i] = arr[i + mid + 1];

        stepCount += 3;
        int leftIndex = 0, rightIndex = 0;
        int arrIndex = start;

        // if leftSize is false, the second condition will not be checked, so only add 1 step.
        stepCount += leftSize > leftIndex ? 2 : 1;
        while (leftSize > leftIndex && rightSize > rightIndex){
            stepCount++;
            if (left[leftIndex] <= right[rightIndex]) {
                stepCount+= 2;
                arr[arrIndex] = left[leftIndex];
                leftIndex++;
            }
            else {
                stepCount+=2;
                arr[arrIndex] = right[rightIndex];
                rightIndex++;
            }
            stepCount++;
            arrIndex++;
            // for the loop: if leftSize is false, the second condition will not be checked, so only add 1 step.
            stepCount += leftSize > leftIndex ? 2 : 1;
        }

        // if left index didnt reach up to leftSize, there are more elements not yet copied in left,
        // so copy them over
        stepCount++;
        while (leftIndex < leftSize){
            stepCount+=3;
            arr[arrIndex] = left[leftIndex];
            leftIndex++;
            arrIndex++;
            stepCount++; // for the loop cmp
        }

        // copy over any remaining elements in right
        stepCount++; // loop cmp
        while (rightIndex < rightSize){
            stepCount += 3;
            arr[arrIndex] = right[rightIndex];
            rightIndex++;
            arrIndex++;
            stepCount++; // loop cmp
        }
    }

    /**
     * Recursive merge sort, which sorts a subarray according to the given indices.
     * @param arr array to sort
     * @param start starting index of subarray to sort, inclusive
     * @param end ending index of subarray to sort, inclusive
     */
    static void mergeSort(int[] arr, int start, int end){
        stepCount++;
        if (start < end)
        {
            // find middle
            stepCount+=3; // 3 steps for addition, division, and assignment
            int mid = (start+end)/2;

            // using indices, separate arr into 2 subarrays, and merge-sort each
            mergeSort(arr, start, mid);
            mergeSort(arr , mid + 1, end);
            // merge the 2 now-sorted subarrays
            merge(arr, start, mid, end);
        }
    }

    /**
     * Execute merge sort on an entire array.
     * Added for easy calling on entire array.
     * @param arr array to sort
     */
    static void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }

    static void insertionSort(int[] arr) {
        stepCount+=2; // for assignment and first cmp
        for (int i = 1; i < arr.length; i++) {
            stepCount++;
            int currentValue = arr[i];
            // swap current value with previous index if current value is smaller
            stepCount+=2;
            int j = i - 1;
            stepCount+= j>= 0 ? 1 : 2; // only 1 cmp will be done if first condition false
            for (; j >= 0 && currentValue < arr[j]; j--) {
                stepCount+=3;//for j+1, assignment, and decrementing j
                arr[j + 1] = arr[j];
            }
            stepCount+=2; // for j+1 and assignment
            arr[j + 1] = currentValue;
            stepCount+=2; // for incrementing i and for cmp
        }
//        for (int i = 1; i < arr.length; i++)
//            if (arr[i] < arr[i-1]) System.err.printf("ERROR, SORT FAILED: %d placed after %d for array of size %d",
//                    arr[i], arr[i-1], arr.length);
    }

    /**
     * Execute the sort corresponding to the given sort code.
     * The purpose of this is to help condense code.
     * @param sortCode sort code
     * @param arr int array to sort
     */
    static void determineSortType(int sortCode, int[] arr){
        if (sortCode == SORTCODE_INSERT)
            SortStepCounting.insertionSort(arr);
        else if (sortCode == SORTCODE_MERGE)
            SortStepCounting.mergeSort(arr);
        else System.err.println("ERROR: INCORRECT SORTCODE GIVEN, NO SORT COMPLETED.");
    }

    static void testSort(int sortCode) throws IOException {
        // record results of sort on increasing, decreasing, randomly permuted, and randomly filled arrays respectively.
        double[] incResults = new double[BOUNDS.length];
        double[] decResults = new double[BOUNDS.length];
        double[] permResults = new double[BOUNDS.length];
        double[] randResults = new double[BOUNDS.length];
        // loop going through each BOUND
        for (int j = 0; j < BOUNDS.length; j++) {
            int n = BOUNDS[j];
            int[] arr;

            arr = new int[n];
            // fill arr in increasing order
            for (int i = 1; i <= n; i++)
                arr[i - 1] = i;
            // sort
            determineSortType(sortCode, arr);
            incResults[j] = stepCount;
            stepCount = 0;

            // DECREASING ORDER
            arr = new int[n];
            for (int i = 1; i <= n; i++)
                arr[i - 1] = n - i + 1;
            determineSortType(sortCode, arr);
            decResults[j] = stepCount;
            stepCount = 0;

            // RANDOM PERMUTATION
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                arr = permuteRandomly(n);
                determineSortType(sortCode, arr);
            }
            stepCount /= AVERAGE_TRAILS_COUNT;
            permResults[j] = stepCount;
            stepCount = 0;

            // RANDOM NUMBERS
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++){
                arr = randomFill(n);
                determineSortType(sortCode, arr);
            }
            stepCount /= AVERAGE_TRAILS_COUNT;
            randResults[j] = stepCount;
            stepCount = 0;
        }
        String header;
        switch (sortCode){
            case SORTCODE_INSERT:
                header = "INSERTION SORT: STEPS";
                break;
            case SORTCODE_MERGE:
                header = "MERGE SORT: STEPS";
                break;
            default:
                header = "NO SORT COMPLETED";
                break;
        }
        SortTimeCounting.printResults(header, incResults, decResults, permResults, randResults);
    }
}