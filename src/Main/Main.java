package Main;
import lab1.MergeSorterTime;

import java.io.IOException;

public class Main {
    //final static int SORTCODE_MERGE = 0, SORTCODE_INSERT = 1;

    public static void main(String[] args) throws IOException {
        /*if (args.length == 0){
            System.err.print("Error: no argument given. ");
            System.out.print("Enter t to calculate time, s to calculate steps.");
            return;
        }

        if (!args[0].equals("t") && !args[0].equals("s")){
            System.err.print("Error: invalid argument given. ");
            System.out.print("Enter t to calculate time, s to calculate steps.");
            return;
        }
        if (args[0].equals("t")) {
            System.out.println("Timing sorts...");
            MergeSorterTime.testSort(SORTCODE_INSERT);
            MergeSorterTime.testSort(SORTCODE_MERGE);
            System.out.println("Complete.");
        }
        else if (args[0].equals("s")){
            System.out.println("Counting steps...");
            InsertionSorterSteps.testSort(SORTCODE_INSERT);
            InsertionSorterSteps.testSort(SORTCODE_MERGE);
            System.out.println("Complete.");
        }
        */
        System.out.println("Testing sorting methods...");
        new MergeSorterTime().testSort();
    }
}
