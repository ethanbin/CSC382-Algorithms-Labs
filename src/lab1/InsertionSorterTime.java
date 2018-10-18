// Written by Ethan Binyaminov
package lab1;

import Sorters.SorterTime;

public class InsertionSorterTime extends SorterTime {
    public InsertionSorterTime() {
        header = "INSERTION SORT: TIME";
    }

    protected void insertionSort(int[] arr) {
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

    @Override
    public void sort(int[] arr) {
        insertionSort(arr);
    }
}