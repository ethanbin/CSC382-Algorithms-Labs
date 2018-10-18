package lab2;

import Sorters.SorterTime;

public class HeapSorterTime extends SorterTime {
    public HeapSorterTime(){ header = "HEAP SORT: TIME";}
    private void buildMaxHeap(int[]arr){
        for (int i = arr.length/2 - 1; i >= 0; i--)
            maxHeapify(arr, i, arr.length);
    }

    /**
     * Max-Heapify a subtree in the given array.
     * @param arr array containing subtree to heapify
     * @param i root of subtree to heapify
     * @param heapSize size of heap
     */
    private void maxHeapify(int[] arr, int i, int heapSize){
        int largest = i; // Initialize largest as root
        int left = 2*i + 1; // left = 2*i + 1, since index starts at 0
        int right = 2*i + 2; // right = 2*i + 2, since index starts at 0

        if (left < heapSize && arr[left] > arr[largest])
            largest = left;
        if (right < heapSize && arr[right] > arr[largest])
            largest = right;

        if (largest != i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, heapSize);
        }
    }

    public void heapSort(int[] arr){
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, 0, i);
        }
    }

    @Override
    public void sort(int[] arr) {
        heapSort(arr);
    }
}
