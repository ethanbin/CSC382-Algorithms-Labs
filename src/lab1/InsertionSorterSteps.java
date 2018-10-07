package lab1;
import Sorters.SorterSteps;
import java.io.IOException;

public class InsertionSorterSteps extends SorterSteps {
    public InsertionSorterSteps() {
        header = "INSERTION SORT: STEPS";
    }

    protected void insertionSort(int[] arr) {
        stepCount+=2; // for assignment and first cmp
        for (int i = 1; i < arr.length; i++) {
            stepCount++;
            int currentValue = arr[i];
            // swap current value with previous index if current value is smaller
            stepCount+=2;
            int j = i - 1;
            stepCount+= j>= 0 ? 1 : 2; // only 1 cmp will be done if first condition false
            for (; j >= 0 && currentValue < arr[j]; j--) {
                stepCount+=3;//for j+1, assignment, and decrementing j
                arr[j + 1] = arr[j];
            }
            stepCount+=2; // for j+1 and assignment
            arr[j + 1] = currentValue;
            stepCount+=2; // for incrementing i and for cmp
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