package lab2;

import Sorters.SorterTime;

public class QuickSorterTime extends SorterTime {
    public QuickSorterTime(){ header = "QUICK SORT: TIME";}
    // returns position of pivot used
    private int partition(int[] arr, int start, int end){
        int pivotValue = arr[end];
        int j = start - 1;
        for (int i = start; i < end; i++){
            if (arr[i] <= pivotValue) {
                j++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap pivot with first element greater than pivot
        int temp = arr[j+1];
        arr[j+1] = arr[end];
        arr[end] = temp;
        return j + 1;
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
        quickSort(arr, 0, arr.length - 1);
    }
}
