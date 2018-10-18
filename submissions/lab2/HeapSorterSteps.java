package lab2;

import Sorters.SorterSteps;

public class HeapSorterSteps extends SorterSteps {
    public HeapSorterSteps(){ header = "HEAP SORT: STEPS";}

    private void buildMaxHeap(int[]arr){
        // 1 for subtraction, 1 for assignment, 1 for CMP
        stepCount+=3;
        for (int i = arr.length/2 - 1; i >= 0; i--){
            maxHeapify(arr, i, arr.length);
            // +2 for decrementing and comparing
            stepCount+=2;
        }
    }

    /**
     * Max-Heapify a subtree in the given array.
     * @param arr array containing subtree to heapify
     * @param i root of subtree to heapify
     * @param heapSize size of heap
     */
    private void maxHeapify(int[] arr, int i, int heapSize){
        stepCount++;
        int largest = i; // largest = root index

        stepCount += 3; // for multiplying, adding, and assigning
        int left = 2*i + 1; // +1, since index starts at 0

        stepCount += 3; // for multiplying, adding, and assigning
        int right = 2*i + 2; // +2, since index starts at 0

        // if first condition in if statement is true, then we check the second condition, doing 2 CMP
        stepCount += left < heapSize ? 2 : 1;
        if (left < heapSize && arr[left] > arr[largest]){
            stepCount++;
            largest = left;
        }
        stepCount += right < heapSize ? 2 : 1;
        if (right < heapSize && arr[right] > arr[largest]){
            stepCount++;
            largest = right;
        }

        stepCount++;
        if (largest != i){
            stepCount+=3;
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, heapSize);
        }
    }

    public void heapSort(int[] arr){
        buildMaxHeap(arr);
        stepCount += 3;
        for (int i = arr.length - 1; i >= 0; i--) {
            stepCount += 3;
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, 0, i);
            // stepcount + 2 because decrementing and comparing
            stepCount+=2;
        }
    }

    @Override
    public void sort(int[] arr) {
        heapSort(arr);
    }
}