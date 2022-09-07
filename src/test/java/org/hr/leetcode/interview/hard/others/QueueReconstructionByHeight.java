package org.hr.leetcode.interview.hard.others;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][];
        Arrays.stream(people).sorted(new HeightSorter()).forEach(m -> {
            int pos = findFreePos(result, m[0], m[1]);
            result[pos] = m;
        });
        return result;
    }

    private int findFreePos(int[][] result, int height, int beforeExpected) {
        int beforeFound = 0, resultPos = 0;
        while (beforeFound < beforeExpected) {
            int[] p = result[resultPos++];
            if (p == null || p[0] >= height) {
                beforeFound++;
            }
        }
        while (result[resultPos] != null) {
            resultPos++;
        }
        return resultPos;
    }

    public static class HeightSorter implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
        }
    }

    @Test
    public void test1() {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] result = {{5,0},{7,0},{5,2},{6,1},{4,4},{7,1}};
        assertThat(reconstructQueue(people), is(result));
    }

    @Test
    public void test2() {
        int[][] people = {{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
        int[][] result = {{4,0},{5,0},{2,2},{3,2},{1,4},{6,0}};
        assertThat(reconstructQueue(people), is(result));
    }
}
