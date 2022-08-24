package org.hr.practice.ipk.dah;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountTriplets {
    static long countTriplets(List<Long> arr, long r) {
        long count = 0;
        long r2 = r*r;
        // Key - number, value - number of pairs we can make to this number
        Map<Long, Long> count2 = new HashMap<>();
        // Key - number, value - number of triplets we can make to this number
        Map<Long, Long> count3 = new HashMap<>();
        for (int i = arr.size()-3; i >= 0; i--) {
            Long num3 = arr.get(i+2);
            count2.merge(num3, 1L, Long::sum);
            Long num2 = arr.get(i+1) * r;
            count3.merge(num2, count2.computeIfAbsent(num2, k -> 0L), Long::sum);

            Long num = arr.get(i) * r2;
            count += count3.computeIfAbsent(num, k -> 0L);
        }
        return count;
    }

    static long countTripletsNonOptimized(List<Long> arr, long r) {
        long count = 0;
        for (int i = 0; i < arr.size() - 2; i++) {
            long ai = arr.get(i);
            for (int j = i+1; j < arr.size() - 1; j++) {
                long aj = arr.get(j);
                for (int k = j+1; k < arr.size(); k++) {
                    long ak = arr.get(k);
                    if (aj == ai * r && ak == aj * r) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        bufferedWriter.write(String.valueOf(test(System.in)));
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    private static long test(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedReader.close();
        return ans;
    }

    @Test
    public void testKnown() {
        assertThat(countTriplets(Arrays.asList(1L, 2L, 2L, 4L), 2), is(2L));
        assertThat(countTriplets(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L), 3), is(6L));
        assertThat(countTriplets(Arrays.asList(1L, 5L, 5L, 25L, 125L), 5), is(4L));
    }

    @Test
    public void testInput03() throws Exception {
        try (InputStream in = new FileInputStream("src/test/resources/hr/practice/ipk/dah/CountTriplets-input03.txt")) {
            assertThat(test(in), is(166661666700000L));
        }
    }
}
