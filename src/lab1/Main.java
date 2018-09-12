package lab1;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    final static int[] BOUNDS = {100, 200, 300, 400, 500, 1000, 4000, 10000};
    final static int AVERAGE_TRAILS_COUNT = 50;
    final static String TABLE_NAME = "results.csv";
    final static int SORTCODE_MERGE = 0, SORTCODE_INSERT = 1;

    // return an array of specified size, filled with random numbers

    /**
     * Creates and returns an array of a specified size, filled with
     * random numbers ranging from 1 to array size, inclusively.
     * @param size size of array to create
     * @return Array of specified size, filled with random numbers
     * @throws Exception when size less than 1
     */
    static int [] randomFill(int size) throws ArrayIndexOutOfBoundsException {
        if (size < 1) throw new ArrayIndexOutOfBoundsException();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            // generate random int from 1 to size
            arr[i] = ThreadLocalRandom.current().nextInt(1, size+1);
        return  arr;
    }

    /**
     * Creates and returns an array of a specified size, filled with
     * numbers ranging from 1 to array size, inclusively, in random order.
     * @param size size of array to create
     * @return an array filled with numbers ranging from 1 to size, inclusively, in random order
     * @throws Exception when size less than 1
     */
    static int[] permuteRandomly(int size) throws ArrayIndexOutOfBoundsException{
        if (size < 1) throw new ArrayIndexOutOfBoundsException();
        List<Integer> list = new ArrayList<>(size);
        // put elements in list
        for (int i = 1; i <= size; i++) list.add(i);
        // use java's built-in method for shuffling collections
        Collections.shuffle(list);
        // map list's Integer elements to int and cast to array
        return list.stream().mapToInt(i->i).toArray();
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        // temp arrays to contain 2 subarrays to merge
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        // copy into left and right their respective subarrays from arr
        for (int i = 0; i < leftSize; i++) left[i] = arr[i + start];
        for (int i = 0; i < rightSize; i++) right[i] = arr[i + mid + 1];

        int leftIndex = 0, rightIndex = 0;
        int arrIndex = start;
        while (leftSize > leftIndex && rightSize > rightIndex){
            if (left[leftIndex] <= right[rightIndex]) {
                arr[arrIndex] = left[leftIndex];
                leftIndex++;
            }
            else {
                arr[arrIndex] = right[rightIndex];
                rightIndex++;
            }
            arrIndex++;
        }

        // if left index didnt reach up to leftSize, there are more elements not yet copied in left,
        // so copy them over
        while (leftIndex < leftSize){
            arr[arrIndex] = left[leftIndex];
            leftIndex++;
            arrIndex++;
        }

        // copy over any remaining elements in right
        while (rightIndex < rightSize){
            arr[arrIndex] = right[rightIndex];
            rightIndex++;
            arrIndex++;
        }
    }

    /**
     * Recursive merge sort, which sorts a subarray according to the given indices.
     * @param arr array to sort
     * @param start starting index of subarray to sort, inclusive
     * @param end ending index of subarray to sort, inclusive
     */
    static void mergeSort(int[] arr, int start, int end){
        if (start < end)
        {
            // find middle
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
        for (int i = 1; i < arr.length; i++) {
            int currentValue = arr[i];
            // swap current value with previous index if current value is smaller
            int j = i - 1;
            for (; j >= 0 && currentValue < arr[j]; j--)
                arr[j + 1] = arr[j];
            arr[j + 1] = currentValue;
        }
//        for (int i = 1; i < arr.length; i++)
//            if (arr[i] < arr[i-1]) System.err.printf("ERROR, SORT FAILED: %d placed after %d for array of size %d",
//                    arr[i], arr[i-1], arr.length);
    }

    // 3 8 5 4 9 0
    // i can iterate through a list, adding each element to a new list using insertion sort
    // or i can just use for loop with get, using one list
    static List<Integer> insertionSort(List<Integer> list){
        if (list.size() < 2) return null;
//        for (ListIterator it = list.listIterator(1); it.hasNext();) {
//            int currentValue = (int) it.next();
//            it.remove();
//            ListIterator insertionIt = list.listIterator(it.previousIndex());
//            while (insertionIt.hasPrevious() && currentValue < (Integer) insertionIt.previous());
//            insertionIt.next();
//            insertionIt.add(currentValue);
//        }
        List<Integer> ret = new LinkedList<>();
        ListIterator it = list.listIterator();
        ret.add((Integer)it.next());
        while (it.hasNext()) {
            int key = (int) it.next();
            System.out.printf("key=%d%n",key);
            ListIterator retIt = ret.listIterator();
            int currentValue = (int) retIt.next();
            //retIt.previous();
            while (retIt.hasNext() && key > currentValue) {
                currentValue = (int) retIt.next();
            }
            //if (retIt.nextIndex() == 1) retIt.next();
            if (retIt.hasPrevious())
                retIt.previous();
            retIt.add(key);
            for (int i = 0; i < ret.size(); i++) System.out.printf("%d, ", ret.get(i));
            System.out.println();
        }
        return ret;
    }

    // append or create and write data to a CSV file
    static void printResults(String header, double[] incResults, double[] decResults, double[] permResults, double[] randResults) throws IOException {
        // begin creating and printing file
        //PrintWriter pw = new PrintWriter(new File(TABLE_NAME));
        FileWriter fw = new FileWriter(TABLE_NAME, true);
        StringBuilder table = new StringBuilder();
        table.append(System.getProperty("line.separator") + header);
        // the line.separator thing will ensure the correct newline specific to the system running this is appended
        table.append(System.getProperty("line.separator"));
        table.append("BOUNDS,");
        for (int n : BOUNDS) {
            table.append(n);
            table.append(",");
        }
        table.append(System.getProperty("line.separator"));
        StringBuilder inc = new StringBuilder("INC,");
        StringBuilder dec = new StringBuilder("DEC,");
        StringBuilder perm = new StringBuilder("PERM,");
        StringBuilder rand = new StringBuilder("RAND,");
        // using string.format to avoid printing scientific notation on the numbers and needless zeros after decimal.
        for (int i = 0; i < BOUNDS.length; i++){
            inc.append(String.format("%.0f", incResults[i]));
            inc.append(',');
            dec.append(String.format("%.0f", decResults[i]));
            dec.append(',');
            perm.append(String.format("%.0f", permResults[i]));
            perm.append(',');
            rand.append(String.format("%.0f", randResults[i]));
            rand.append(',');
        }
        table.append(inc);
        table.append(System.getProperty("line.separator"));
        table.append(dec);
        table.append(System.getProperty("line.separator"));
        table.append(perm);
        table.append(System.getProperty("line.separator"));
        table.append(rand);
        table.append(System.getProperty("line.separator"));
        fw.append(table.toString());
        fw.close();
        System.out.println(table.toString().replace(',', '\t'));
    }

    /**
     * Execute the sort corresponding to the given sort code.
     * The purpose of this is to help condense code.
     * @param sortCode sort code
     * @param arr int array to sort
     */
    static void determineSortType(int sortCode, int[] arr){
        if (sortCode == SORTCODE_INSERT)
            insertionSort(arr);
        else if (sortCode == SORTCODE_MERGE)
            mergeSort(arr);
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
            long startTime = 0, finishTime = 0, average = 0;

            // INCREASING ORDER
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                arr = new int[n];
                // fill arr in increasing order
                for (int i = 1; i <= n; i++)
                    arr[i - 1] = i;
                // get time at start
                startTime = System.nanoTime();
                // sort
                determineSortType(sortCode, arr);
                // get time at end
                finishTime = System.nanoTime();
                average += finishTime - startTime;
            }
            average /= AVERAGE_TRAILS_COUNT;
            incResults[j] = average;
            average = 0;

            // DECREASING ORDER
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                arr = new int[n];
                for (int i = 1; i <= n; i++)
                    arr[i - 1] = n - i + 1;
                startTime = System.nanoTime();
                determineSortType(sortCode, arr);
                finishTime = System.nanoTime();
                average += finishTime - startTime;
            }
            average /= AVERAGE_TRAILS_COUNT;
            decResults[j] = average;
            average = 0;

            // RANDOM PERMUTATION
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                arr = permuteRandomly(n);
                startTime = System.nanoTime();
                determineSortType(sortCode, arr);
                finishTime = System.nanoTime();
                average += finishTime - startTime;
            }
            average /= AVERAGE_TRAILS_COUNT;
            permResults[j] = average;
            average = 0;

            // RANDOM NUMBERS
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++){
                arr = randomFill(n);
                startTime = System.nanoTime();
                determineSortType(sortCode, arr);
                finishTime = System.nanoTime();
                average += (finishTime - startTime);
            }
            average /= AVERAGE_TRAILS_COUNT;
            randResults[j] = average;
            average = 0;
        }
        String header;
        switch (sortCode){
            case SORTCODE_INSERT:
                header = "INSERTION SORT";
                break;
            case SORTCODE_MERGE:
                header = "MERGE SORT";
                break;
            default:
                header = "NO SORT COMPLETED";
                break;
        }
        printResults(header, incResults, decResults, permResults, randResults);
    }

    public static void main(String[] args) throws IOException {
        testSort(SORTCODE_INSERT);
        testSort(SORTCODE_MERGE);
    }
}
