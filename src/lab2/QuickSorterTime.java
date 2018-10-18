package lab2;

import Sorters.SorterTime;

public class QuickSorterTime extends SorterTime {
    // returns position of pivot used
    private int partition(int[] arr, int start, int end){
        int pivot = arr[end-1];
        
        return -1;
    }

    public void quickSort(int[] arr, int start, int end){
        if (start < end){
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr,pivot+1, end);
        }
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }
}
