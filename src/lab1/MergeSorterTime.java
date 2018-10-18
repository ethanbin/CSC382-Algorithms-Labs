package lab1;
import Sorters.SorterTime;

public class MergeSorterTime extends SorterTime {
    public MergeSorterTime() {
        header = "MERGE SORT: TIME";
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        // temp arrays to contain 2 subarrays to merge
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        // copy into left and right their respective subarrays from arr
        for (int i = 0; i < leftSize; i++) left[i] = arr[i + start];
        for (int i = 0; i < rightSize; i++) right[i] = arr[i + mid + 1];

        int leftIndex = 0, rightIndex = 0;
        int arrIndex = start;
        while (leftSize > leftIndex && rightSize > rightIndex){
            if (left[leftIndex] <= right[rightIndex]) {
                arr[arrIndex] = left[leftIndex];
                leftIndex++;
            }
            else {
                arr[arrIndex] = right[rightIndex];
                rightIndex++;
            }
            arrIndex++;
        }

        // if left index didnt reach up to leftSize, there are more elements not yet copied in left,
        // so copy them over
        while (leftIndex < leftSize){
            arr[arrIndex] = left[leftIndex];
            leftIndex++;
            arrIndex++;
        }

        // copy over any remaining elements in right
        while (rightIndex < rightSize){
            arr[arrIndex] = right[rightIndex];
            rightIndex++;
            arrIndex++;
        }
    }

    /**
     * Recursive merge sort, which sorts a subarray according to the given indices.
     * @param arr array to sort
     * @param start starting index of subarray to sort, inclusive
     * @param end ending index of subarray to sort, inclusive
     */
    public void mergeSort(int[] arr, int start, int end){
        if (start < end)
        {
            // find middle
            int mid = (start+end)/2;

            // using indices, separate arr into 2 subarrays, and merge-sort each
            mergeSort(arr, start, mid);
            mergeSort(arr , mid + 1, end);
            // merge the 2 now-sorted subarrays
            merge(arr, start, mid, end);
        }
    }

    /**
     * Execute merge sort on an entire array.
     * Added for easy calling on entire array.
     * @param arr array to sort
     */
    public void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }

    @Override
    public void sort(int[] arr){
        mergeSort(arr, 0, arr.length-1);
    }
}