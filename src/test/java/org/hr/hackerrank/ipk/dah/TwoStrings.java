package org.hr.hackerrank.ipk.dah;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TwoStrings {

    static boolean twoStrings(String s1, String s2) {
        BitSet chars = new BitSet(256);
        for (int i = 0; i < s1.length(); i++) {
            chars.set(s1.charAt(i));
        }
        for (int i = 0; i < s2.length(); i++) {
            if (chars.get(s2.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            boolean result = twoStrings(s1, s2);

            bufferedWriter.write(result ? "YES" : "NO");
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    @Test
    public void testKnown() {
        assertThat(twoStrings("hello", "world"), is(true));
        assertThat(twoStrings("hi", "world"), is(false));
    }
}
