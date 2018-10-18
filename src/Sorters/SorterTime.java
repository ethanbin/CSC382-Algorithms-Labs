package Sorters;

import java.io.IOException;

public abstract class SorterTime extends Sorter {
    @Override
    public void testSort() throws IOException {
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
                sort(arr);
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
                sort(arr);
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
                sort(arr);
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
                sort(arr);
                finishTime = System.nanoTime();
                average += (finishTime - startTime);
            }
            average /= AVERAGE_TRAILS_COUNT;
            randResults[j] = average;
            average = 0;
        }
        printResults(header, incResults, decResults, permResults, randResults);
    }
}