package org.hr.leetcode.interview.hard.sortingAndSearching;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class KthSmallestElementInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int[] pos = new int[matrix.length];
        for (int i = 0; i < k; i++) {
            int minRow = -1;
            for (int j = 0; j < matrix.length; j++) {
                if (pos[j] < matrix[j].length && (minRow == -1 || matrix[j][pos[j]] < matrix[minRow][pos[minRow]])) {
                    minRow = j;
                }
            }
            if (i == k-1) {
                return matrix[minRow][pos[minRow]];
            } else {
                pos[minRow]++;
            }
        }
        return 0;
    }

    @Test
    public void test1() {
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        assertThat(kthSmallest(matrix, 8), is(13));
    }

    @Test
    public void test4() {
        int[][] matrix = {{1,3,5},{6,7,12},{11,14,14}};
        assertThat(kthSmallest(matrix, 2), is(3));
    }

    @Test
    public void test3() {
        int[][] matrix = {{1,2},{1,3}};
        assertThat(kthSmallest(matrix, 2), is(1));
    }

    @Test
    public void test2() {
        int[][] matrix = {{-5}};
        assertThat(kthSmallest(matrix, 1), is(-5));
    }
}
