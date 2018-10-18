// Written by Ethan Binyaminov
package Main;
import lab2.HeapSorterSteps;
import lab2.HeapSorterTime;
import lab2.QuickSorterSteps;
import lab2.QuickSorterTime;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Testing sorting methods...");
        System.out.println("Completed 0/4");

        new QuickSorterTime().testSort();
        System.out.println("Completed 1/4");

        new QuickSorterSteps().testSort();
        System.out.println("Completed 2/4");

        new HeapSorterTime().testSort();
        System.out.println("Completed 3/4");

        new HeapSorterSteps().testSort();
        System.out.println("Completed 4/4");
        System.out.println("Finished.");
    }
}