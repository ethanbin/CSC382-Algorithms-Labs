package lab1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    final static int[] BOUNDS = {100, 200, 300, 400, 500, 1000, 4000, 10000};
    final static int AVERAGE_TRAILS_COUNT = 50;

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

    static int[] mergeSort(int[] arr){
        return null;
    }

    static int[] insertionSort(int[] arr){
        return null;
    }

    static int[] insertionSort(List<Integer> arr){
        return null;
    }

    public static void main(String[] args) {
        // for each loop going through each n
        for (int n : BOUNDS) {
            long startTime, finishTime;

            // INCREASING ORDER
            int[] arr = new int[n];
            for (int i = 1; i <= n; i++) arr[i - 1] = i;
            // get time at start
            startTime = System.nanoTime();
            // sort
            insertionSort(arr);
            // get time at end
            finishTime = System.nanoTime();

            // DECREASING ORDER
            arr = new int[n];
            for (int i = 1; i <= n; i++) arr[i - 1] = n - i + 1;
            startTime = System.nanoTime();
            insertionSort(arr);
            finishTime = System.nanoTime();

            // RANDOM PERMUTATION
            arr = permuteRandomly(n);
            startTime = System.nanoTime();
            insertionSort(arr);
            finishTime = System.nanoTime();

            // RANDOM NUMBERS
            long average = 0;
            for (int i = 0; i < AVERAGE_TRAILS_COUNT; i++){
                arr = randomFill(n);
                startTime = System.nanoTime();
                insertionSort(arr);
                finishTime = System.nanoTime();
                average = startTime - finishTime;
            }
            average /= AVERAGE_TRAILS_COUNT;
        }
    }
}
