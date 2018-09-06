package lab1;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
        for (int i = 1; i <= size; i++) list.add(i);
        Collections.shuffle(list);
        return list.stream().mapToInt(i->i).toArray();
    }

    static void mergeSort(int[] arr){
    }

    // 3 8 1 4 9 0
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currentValue = arr[i];
            // swap current value with previous index if current value is smaller
            int j = i - 1;
            for (; j >= 0 && currentValue < arr[j]; j--)
                arr[j + 1] = arr[j];
            arr[j + 1] = currentValue;
        }
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i-1]) System.err.printf("ERROR, SORT FAILED: %d placed after %d for array of size %d",
                    arr[i], arr[i-1], arr.length);
    }

    static void insertionSort(List<Integer> arr){
    }

    // append or create and write data to a CSV file
    static void printResults(double[] incResults, double[] decResults, double[] permResults, double[] randResults) throws IOException {
        // begin creating and printing file
        //PrintWriter pw = new PrintWriter(new File(TABLE_NAME));
        FileWriter fw = new FileWriter(TABLE_NAME, true);
        StringBuilder table = new StringBuilder();
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
        printResults(incResults, decResults, permResults, randResults);
    }

    public static void main(String[] args) throws IOException {
        testSort(SORTCODE_INSERT);
        testSort(SORTCODE_MERGE);
    }
}
