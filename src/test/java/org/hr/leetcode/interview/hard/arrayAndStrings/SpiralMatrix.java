package org.hr.leetcode.interview.hard.arrayAndStrings;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int x0 = 0, y0 = 0, nx = matrix[0].length-1, ny = matrix.length-1;
        List<Integer> result = new ArrayList<>();
        while (true) {
            if (nx == 0 || ny == 0) {
                if (nx == 0 && ny == 0) {
                    nx = 1;
                } else if (nx == 0) {
                    ny++;
                } else {
                    nx++;
                }
            }
            for (int x = 0; x < nx; x++) {
                result.add(matrix[y0][x0 + x]);
            }
            for (int y = 0; y < ny; y++) {
                result.add(matrix[y0 + y][x0 + nx]);
            }
            if (nx == 0 || ny == 0) break;
            for (int x = 0; x < nx; x++) {
                result.add(matrix[y0 + ny][x0 + nx - x]);
            }
            for (int y = 0; y < ny; y++) {
                result.add(matrix[y0+ny-y][x0]);
            }
            if (nx == 1 || ny == 1) break;
            x0++;
            y0++;
            nx -= 2;
            ny -= 2;
        }
        return result;
    }

    @Test
    public void test1() {
        {
            int[][] matrix = {{1}};
            assertThat(spiralOrder(matrix), is(List.of(1)));
        }
        {
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            assertThat(spiralOrder(matrix), is(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5)));
        }
        {
            int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
            assertThat(spiralOrder(matrix), is(List.of(1, 2, 3, 4, 5, 10, 9, 8, 7, 6)));
        }
        {
            int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
            assertThat(spiralOrder(matrix), is(List.of(1, 2, 3, 4, 5, 10, 15, 14, 13, 12, 11, 6, 7, 8, 9)));
        }
    }
}
