import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MergeInsertionSort {


    public static void sort(int[] a, int k) throws FileNotFoundException {
        int length = a.length;
        if (length > k) {

            // split the array in half
            int middle = length / 2; // determine the middle
            int[] aLeft = Arrays.copyOfRange(a, 0, middle); // array of left side
            int[] aRight = Arrays.copyOfRange(a, middle, length); // array of right side

            sort(aLeft, k); // recursive call: sort left half of array
            sort(aRight, k); // recursive call: sort right half of array

            // merge
            int lengthLeft = aLeft.length;
            int lengthRight = aRight.length;

            int indexLeft = 0; // for iterating over the left array
            int indexRight = 0; // for iterating over the right array
            int indexResult = 0; // for iterating over the merged array

            while (indexLeft < lengthLeft && indexRight < lengthRight) {
                if (aLeft[indexLeft] <= aRight[indexRight]) {
                    a[indexResult] = aLeft[indexLeft++];
                } else {
                    a[indexResult] = aRight[indexRight++];
                }
                indexResult++;
            }

            while (indexLeft < lengthLeft) {
                a[indexResult++] = aLeft[indexLeft++];
            }
            while (indexRight < lengthRight) {
                a[indexResult++] = aRight[indexRight++];
            }

        } else {
            for (int i = 1; i < a.length; i++) {
                int key = a[i];
                int j = i;
                while (j > 0 && a[j - 1] > key) {
                    a[j] = a[j - 1];
                    j--;
                }
                a[j] = key;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> liste = new ArrayList<Integer>();

        // read file
        String path = args[0];
        Scanner read = new Scanner(new File(path));
        while (read.hasNextLine()) {
            liste.add(Integer.valueOf(read.nextLine()));
        }
        read.close();

        // copy arraylist into standard java array for mergesort
        int[] arr = new int[liste.size()];
        for (int i = 0; i < liste.size(); i++) {
            arr[i] = liste.get(i);
        }

        int k = Integer.parseInt(args[1]); // get value of k from arguments

        // sort array and measure runtime of algorithm
        final long timeStart = System.nanoTime();
        sort(arr, k);
        final long timeEnd = System.nanoTime();
        for (int u = 0; u < arr.length; u++) {
            System.out.println(arr[u] + " ");
        }
        System.out.println("Lauf des Sortieralgoruthmus: " + (timeEnd - timeStart) + "Nanosek.");
    }
}
