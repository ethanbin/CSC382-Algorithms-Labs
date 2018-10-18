// Written by Ethan Binyaminov
package Sorters;

import java.io.IOException;

public abstract class SorterSteps extends Sorter {
    protected double stepCount = 0;
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

            arr = new int[n];
            // fill arr in increasing order
            for (int i = 1; i <= n; i++)
                arr[i - 1] = i;
            // sort
            sort(arr);
            incResults[j] = stepCount;
            stepCount = 0;

            // DECREASING ORDER
            arr = new int[n];
            for (int i = 1; i <= n; i++)
                arr[i - 1] = n - i + 1;
            sort(arr);
            decResults[j] = stepCount;
            stepCount = 0;

            // RANDOM PERMUTATION
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                arr = permuteRandomly(n);
                sort(arr);
            }
            stepCount /= AVERAGE_TRAILS_COUNT;
            permResults[j] = stepCount;
            stepCount = 0;

            // RANDOM NUMBERS
            for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++){
                arr = randomFill(n);
                sort(arr);
            }
            stepCount /= AVERAGE_TRAILS_COUNT;
            randResults[j] = stepCount;
            stepCount = 0;
        }
        printResults(header, incResults, decResults, permResults, randResults);
    }
}