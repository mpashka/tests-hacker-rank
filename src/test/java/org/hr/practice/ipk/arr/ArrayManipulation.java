package org.hr.practice.ipk.arr;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class ArrayManipulation {

    static long arrayManipulation(int n, int[][] queries) {
        long[] result = new long[n];
        for (int[] query : queries) {
            int begin = query[0] - 1;
            int add = query[2];
            result[begin] += add;
            int end = query[1];
            if (result.length > end) {
                result[end] -= add;
            }
        }
        long max = result[0];
        long val = 0;
        for (long l : result) {
            val += l;
            max = Math.max(max, val);
        }
        return max;
    }

    static long arrayManipulationComplex(int n, int[][] queries) {
        NavigableMap<Integer, Elem> result = new TreeMap<>();
        result.put(0, new Elem(n, 0));

        for (int i = 0; i < queries.length; i++) {
            int beginIdx = queries[i][0]-1;
            int endIdx = queries[i][1]-1;
            int add = queries[i][2];
            add(result, beginIdx, endIdx, add);
        }
        long max = result.firstEntry().getValue().value;
        for (Elem value : result.values()) {
            max = Math.max(max, value.value);
        }
        return max;
    }

    /**
     *
     * @param result
     * @param beginIdx
     * @param endIdx inclusive
     * @param add
     */
    private static void add(NavigableMap<Integer, Elem> result, int beginIdx, int endIdx, int add) {
        Map.Entry<Integer, Elem> beginEntry = result.floorEntry(beginIdx);
        if (beginEntry.getKey() < beginIdx) {
            result.put(beginIdx, new Elem(beginEntry.getValue().end, beginEntry.getValue().value));
            beginEntry.getValue().end = beginIdx-1;
        } else {

        }

        Map.Entry<Integer, Elem> endEntry = result.floorEntry(endIdx);
        if (endEntry.getValue().end > endIdx) {
            result.put(endIdx+1, new Elem(endEntry.getValue().end, endEntry.getValue().value));
            endEntry.getValue().end = endIdx;
        }

        result.subMap(beginIdx, true, endIdx, true).values().forEach(v -> v.value += add);
    }

    static class Elem {
        private int end;
        private long value;

        public Elem(int end, long value) {
            this.end = end;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Elem elem = (Elem) o;
            return end == elem.end && value == elem.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(end, value);
        }

        @Override
        public String toString() {
            return "{" + end + ":" + value + '}';
        }
    }

    static long arrayManipulationNonOptimized(int n, int[][] queries) {
        long[] result = new long[n];
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]-1; j < queries[i][1]; j++) {
                result[j] += queries[i][2];
            }
        }
        long max = result[0];
        for (int i = 0; i < result.length; i++) {
            max = Math.max(max, result[i]);
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    @Test
    public void testKnown() {
        assertThat(arrayManipulation(10, new int[][]{
                {1, 5, 3},
                {4, 8, 7},
                {6, 9, 1},
        }), is(10L));
        assertThat(arrayManipulation(10, new int[][]{
                {2, 6, 8},
                {3, 5, 7},
                {1, 8, 1},
                {5, 9, 15},
        }), is(31L));
        assertThat(arrayManipulation(5, new int[][]{
                {1, 2, 100},
                {2, 5, 100},
                {3, 4, 100},
        }), is(200L));
    }

    @Test
    public void testAdd() {
        NavigableMap<Integer, Elem> result = new TreeMap<>();
        result.put(0, new Elem(9, 0));
        add(result, 0, 0, 1);
        assertThat(result, hasEntry(0, new Elem(0, 1)));
        assertThat(result, hasEntry(1, new Elem(9, 0)));

        add(result, 1, 2, 2);
        assertThat(result, hasEntry(0, new Elem(0, 1)));
        assertThat(result, hasEntry(1, new Elem(2, 2)));
        assertThat(result, hasEntry(3, new Elem(9, 0)));

        add(result, 1, 2, 2);
        assertThat(result, hasEntry(0, new Elem(0, 1)));
        assertThat(result, hasEntry(1, new Elem(2, 4)));
        assertThat(result, hasEntry(3, new Elem(9, 0)));

        add(result, 9, 9, 3);
        assertThat(result, hasEntry(0, new Elem(0, 1)));
        assertThat(result, hasEntry(1, new Elem(2, 4)));
        assertThat(result, hasEntry(3, new Elem(8, 0)));
        assertThat(result, hasEntry(9, new Elem(9, 3)));

        add(result, 4, 6, 5);
        assertThat(result, hasEntry(0, new Elem(0, 1)));
        assertThat(result, hasEntry(1, new Elem(2, 4)));
        assertThat(result, hasEntry(3, new Elem(3, 0)));
        assertThat(result, hasEntry(4, new Elem(6, 5)));
        assertThat(result, hasEntry(9, new Elem(9, 3)));
    }
}
