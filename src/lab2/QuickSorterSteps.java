// Written by Ethan Binyaminov
package lab2;

import Sorters.SorterSteps;

public class QuickSorterSteps extends SorterSteps {
    public QuickSorterSteps(){ header = "QUICK SORT: STEPS";}

    // returns position of pivot used
    private int partition(int[] arr, int start, int end){
        stepCount++;
        int pivotValue = arr[end];
        stepCount++;
        int j = start - 1;
        stepCount+=2;
        for (int i = start; i < end; i++){
            stepCount++;
            if (arr[i] <= pivotValue) {
                stepCount+=4;
                j++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            stepCount += 2; // for i++ and i < end
        }
        stepCount+=3;
        // swap pivot with first element greater than pivot
        int temp = arr[j+1];
        arr[j+1] = arr[end];
        arr[end] = temp;
        stepCount++;
        return j + 1;
    }

    public void quickSort(int[] arr, int start, int end){
        stepCount++;
        if (start < end){
            stepCount++;
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr,pivot+1, end);
        }
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }
}