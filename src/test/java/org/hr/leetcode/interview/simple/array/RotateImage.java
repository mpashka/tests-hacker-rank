package org.hr.leetcode.interview.simple.array;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int c = n-1;

        int maxX, maxY;
        if (n % 2 == 0) {
            maxX = maxY = n;
        } else {
            maxX = n+1;
            maxY = n-1;
        }
        for (int y0 = 0; y0 < maxY; y0 += 2) {
            for (int x0 = 0; x0 < maxX; x0 += 2) {
                int next = matrix[y0/2][x0/2];
                int dx = c-x0, dy = c-y0;
                for (int i = 0; i < 3; i++) {
                    int kxdx = ((i+2)%4 - 2) % 2, kxdy = ((i+3)%4 - 2) % 2, kydx = ((5-i)%4 - 2) % 2, kydy = ((4-i)%4 - 2) % 2;
                    int x1 = c + kxdx*dx + kxdy*dy, y1 = c + kydx*dx + kydy*dy;
                    int next1 = matrix[y1/2][x1/2];
                    matrix[y1/2][x1/2] = next;
                    next = next1;
                }
                matrix[y0/2][x0/2] = next;
            }
        }
    }

    @Test
    public void test() {
        {
            int[][] matrix = {{1}};
            int[][] result = {{1}};
            rotate(matrix);
            assertThat(matrix, is(result));
        }

        {
            int[][] matrix = {{1,2},{3,4}};
            int[][] result = {{3,1},{4,2}};
            rotate(matrix);
            assertThat(matrix, is(result));
        }

        {
            int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
            int[][] result = {{7,4,1},{8,5,2},{9,6,3}};
            rotate(matrix);
            assertThat(matrix, is(result));
        }

        {
            int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
            int[][] result = {{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}};
            rotate(matrix);
            assertThat(matrix, is(result));
        }
    }
}
