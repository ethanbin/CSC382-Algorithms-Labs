// Written by Ethan Binyaminov
package lab1;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static lab1.MergeSorterTime.*;

public class LinkedSorting {
    /*
    static List<Integer> insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;
            ListIterator<Integer> it = list.listIterator();
            int currentValue = it.next();
            while (it.hasNext() && key > currentValue)
                currentValue = it.next();
            //if (retIt.nextIndex() == 1) retIt.next();
            if (it.hasPrevious())
                it.previous();
            it.add(key);
            while (it.nextIndex() != i)
                it.next();
            it.remove();
//            for (int i = 0; i < ret.size(); i++) System.out.printf("%d, ", ret.get(i));
//            System.out.println();
        }
        for (int i = 1; i < list.size(); i++)
            if (list.get(i-1) > list.get(i)) System.err.println("UNSORTED");
        return list;

    }


    // i can iterate through a list, adding each element to a new list using insertion sort
    // or i can just use for loop with get, using one list
    static List<Integer> insertionSortIntoNewList(List<Integer> list){
        if (list.size() < 2) return null;
//        for (ListIterator it = list.listIterator(1); it.hasNext();) {
//            int currentValue = (int) it.next();
//            it.remove();
//            ListIterator insertionIt = list.listIterator(it.previousIndex());
//            while (insertionIt.hasPrevious() && currentValue < (Integer) insertionIt.previous());
//            insertionIt.next();
//            insertionIt.add(currentValue);
//        }
        List<Integer> ret = new LinkedList<>();
        ListIterator<Integer> it = list.listIterator();
        ret.add((Integer)it.next());
        while (it.hasNext()) {
            int key = (int) it.next();
            //System.out.printf("key=%d%n",key);
            ListIterator<Integer> retIt = ret.listIterator();
            int currentValue = (int) retIt.next();
            //retIt.previous();
            while (retIt.hasNext() && key > currentValue) {
                currentValue = (int) retIt.next();
            }
            //if (retIt.nextIndex() == 1) retIt.next();
            if (retIt.hasPrevious())
                retIt.previous();
            retIt.add(key);
//            for (int i = 0; i < ret.size(); i++) System.out.printf("%d, ", ret.get(i));
//            System.out.println();
        }
        return ret;
    }

    static void testSort() throws IOException {
        // record results of sort on increasing, decreasing, randomly permuted, and randomly filled arrays respectively.
        double[] incResults = new double[BOUNDS.length];
        double[] decResults = new double[BOUNDS.length];
        double[] permResults = new double[BOUNDS.length];
        double[] randResults = new double[BOUNDS.length];
        // loop going through each BOUND
        List<Integer> list = new LinkedList<>();
        for (int j = 0; j < BOUNDS.length; j++) {
            int n = BOUNDS[j];
            long startTime = 0, finishTime = 0, average = 0;

            // INCREASING ORDER
            //for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                list.clear();
                // fill arr in increasing order
                for (int i = 1; i <= n; i++)
                    list.add(i);
                // get time at start
                startTime = System.nanoTime();
                // sort
                insertionSort(list);
                // get time at end
                finishTime = System.nanoTime();
                average += finishTime - startTime;
            //}
            average /= AVERAGE_TRAILS_COUNT;
            incResults[j] = average;
            average = 0;
            System.out.println("finished increasing");

            // DECREASING ORDER
            //for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                list.clear();
                for (int i = 1; i <= n; i++)
                    list.add(n - i + 1);
                startTime = System.nanoTime();
                insertionSort(list);
                finishTime = System.nanoTime();
                average += finishTime - startTime;
            //}
            average /= AVERAGE_TRAILS_COUNT;
            decResults[j] = average;
            average = 0;
            System.out.println("finished decreasing");

            // RANDOM PERMUTATION
            //for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++) {
                list.clear();
                for (int i = 1; i <= n; i++) list.add(i);
                Collections.shuffle(list);
                startTime = System.nanoTime();
                insertionSort(list);
                finishTime = System.nanoTime();
                average += finishTime - startTime;
            //}
            average /= AVERAGE_TRAILS_COUNT;
            permResults[j] = average;
            average = 0;
            System.out.println("finished rand perm");

            // RANDOM NUMBERS
            //for (int k = 0; k < AVERAGE_TRAILS_COUNT; k++){
                list.clear();
                for (int i = 0; i < n; i++)
                    list.add(ThreadLocalRandom.current().nextInt(1, n+1));
                startTime = System.nanoTime();
                insertionSort(list);
                finishTime = System.nanoTime();
                average += (finishTime - startTime);
            //}
            average /= AVERAGE_TRAILS_COUNT;
            randResults[j] = average;
            average = 0;
            System.out.println("finished rand");
        }

        String header = "LINKED LIST INSERTION SORT: TIME";
        printResults(header, incResults, decResults, permResults, randResults);
    }

    public static void main(String[] args) throws IOException {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10; i++) l.add(i+5);
        ListIterator it = l.listIterator();
        System.out.println(it.nextIndex());
        System.out.println(it.next());
        System.out.println(it.nextIndex());
        System.out.println(it.next());
        System.out.println(it.nextIndex());
        System.out.println(it.previous());
        System.out.println(it.previous());
        //testSort();
    }
*/
}