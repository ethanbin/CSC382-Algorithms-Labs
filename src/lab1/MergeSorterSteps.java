// Written by Ethan Binyaminov
package lab1;
import Sorters.SorterSteps;

public class MergeSorterSteps extends SorterSteps {
    public MergeSorterSteps() {
        header = "MERGE SORT: STEPS";
    }

    private void merge(int[] arr, int start, int mid, int end){
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
    public void mergeSort(int[] arr, int start, int end){
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
    public void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }
}