package org.hr.hackerrank.ipk.arr;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class MinimumSwaps2 {

    static int minimumSwaps(int[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            int el = arr[i]-1;
            if (el != i) {
                arr[i] = arr[el];
                arr[el] = el+1;
                swaps++;
                i--;
            }
        }
        return swaps;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    @Test
    public void testKnown() {
        assertThat(minimumSwaps(new int[]{7, 1, 3, 2, 4, 5, 6}), is(5));
        assertThat(minimumSwaps(new int[]{4, 3, 1, 2}), is(3));
        assertThat(minimumSwaps(new int[]{2, 3, 4, 1, 5}), is(3));
        assertThat(minimumSwaps(new int[]{1, 3, 5, 2, 4, 6, 7}), is(3));
    }

}
