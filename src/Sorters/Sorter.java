package Sorters;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Sorter {
    protected final static int[] BOUNDS = {100, 200, 300, 400, 500, 1000, 4000, 10000};
    protected final static int AVERAGE_TRAILS_COUNT = 50;
    protected final static String TABLE_NAME = "results.csv";
    protected String header = "NO SORT COMPLETED";

    /**
     * Creates and returns an array of a specified size, filled with
     * random numbers ranging from 1 to array size, inclusively.
     * @param size size of array to create
     * @return Array of specified size, filled with random numbers
     * @throws Exception when size less than 1
     */
    public int [] randomFill(int size) throws ArrayIndexOutOfBoundsException {
        if (size < 1) throw new ArrayIndexOutOfBoundsException();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            // generate random int from 1 to size
            arr[i] = ThreadLocalRandom.current().nextInt(1, size+1);
        return  arr;
    }

    /**
     * Creates and returns an array of a specified size, filled with
     * numbers ranging from 1 to array size, inclusively, in random order.
     * @param size size of array to create
     * @return an array filled with numbers ranging from 1 to size, inclusively, in random order
     * @throws Exception when size less than 1
     */
    public int[] permuteRandomly(int size) throws ArrayIndexOutOfBoundsException{
        if (size < 1) throw new ArrayIndexOutOfBoundsException();
        List<Integer> list = new ArrayList<>(size);
        // put elements in list
        for (int i = 1; i <= size; i++) list.add(i);
        // use java's built-in method for shuffling collections
        Collections.shuffle(list);
        // map list's Integer elements to int and cast to array
        return list.stream().mapToInt(i->i).toArray();
    }

    public abstract void sort(int[] arr);

    // append or create and write data to a CSV file
    protected final void printResults(String header, double[] incResults, double[] decResults, double[] permResults, double[] randResults) throws IOException {
        // begin creating and printing file
        //PrintWriter pw = new PrintWriter(new File(TABLE_NAME));
        FileWriter fw = new FileWriter(TABLE_NAME, true);
        StringBuilder table = new StringBuilder();
        table.append(System.getProperty("line.separator") + header);
        // the line.separator thing will ensure the correct newline specific to the system running this is appended
        table.append(System.getProperty("line.separator"));
        table.append("BOUNDS,");
        for (int n : BOUNDS) {
            table.append(n);
            table.append(",");
        }
        table.append(System.getProperty("line.separator"));
        StringBuilder inc = new StringBuilder("INC,");
        StringBuilder dec = new StringBuilder("DEC,");
        StringBuilder perm = new StringBuilder("PERM,");
        StringBuilder rand = new StringBuilder("RAND,");
        // using string.format to avoid printing scientific notation on the numbers and needless zeros after decimal.
        for (int i = 0; i < BOUNDS.length; i++){
            inc.append(String.format("%.0f", incResults[i]));
            inc.append(',');
            dec.append(String.format("%.0f", decResults[i]));
            dec.append(',');
            perm.append(String.format("%.0f", permResults[i]));
            perm.append(',');
            rand.append(String.format("%.0f", randResults[i]));
            rand.append(',');
        }
        table.append(inc);
        table.append(System.getProperty("line.separator"));
        table.append(dec);
        table.append(System.getProperty("line.separator"));
        table.append(perm);
        table.append(System.getProperty("line.separator"));
        table.append(rand);
        table.append(System.getProperty("line.separator"));
        fw.append(table.toString());
        fw.close();
        System.out.println(table.toString().replace(',', '\t'));
    }

    abstract public void testSort() throws IOException;
}