package org.hr.practice.ipk.dah;

import org.junit.Test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RansomNote {
    static boolean checkMagazineNoCount(String[] magazine, String[] note) {
        Set<String> words = new HashSet<>(Arrays.asList(magazine));
        for (String word : note) {
            if (!words.contains(word)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> words = new HashMap<>();
        for (String word : magazine) {
            words.merge(word, 1, Integer::sum);
        }
        for (String word : note) {
            Integer cnt = words.computeIfPresent(word, (w, i) -> i - 1);
            if (cnt == null || cnt < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean test(InputStream in) {
        Scanner scanner = new Scanner(in);
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }
        scanner.close();
        return checkMagazine(magazine, note);
    }

    public static void main(String[] args) {
        System.out.println(test(System.in) ? "Yes" : "No");
    }

    @Test
    public void testKnown() {
        assertThat(checkMagazine(new String[]{"give", "me", "one", "grand", "today", "night"},
                new String[]{"give", "one", "grand", "today"}), is(true));
        assertThat(checkMagazine(new String[]{"two", "times", "three", "is", "not", "four"},
                new String[]{"two", "times", "two", "is", "four"}), is(false));
        assertThat(checkMagazine(new String[]{"ive", "got", "a", "lovely", "bunch", "of", "coconuts"},
                new String[]{"ive", "got", "some", "coconuts"}), is(false));
    }

    @Test
    public void testInput13() throws Exception {
        try (InputStream in = new FileInputStream("src/test/resources/hr/practice/ipk/dah/RansomNote-input13.txt")) {
            assertThat(test(in), is(true));
        }
    }
}
