package org.hr.leetcode.collection.medium.arrayAndStrings;

import org.hr.utils.MatrixConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.BitSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SetMatrixZeroes {
    /**
     * o(1) memory usage
     */
    public void setZeroes(int[][] matrix) {
        int rowN = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 0) {
                    matrix[y][0] = matrix[0][x] = 0;
                    if (rowN == y) {
                        rowN++;
                    }
                }
            }
        }

        boolean fillRowN = false;
        // copy zero values row
        if (rowN != 0) {
            if (rowN >= matrix.length) {
                // all zeroes
                for (int[] row : matrix) {
                    Arrays.fill(row, 0);
                }
                return;
            } else {
                // Copy zero marks to rowN
                fillRowN = matrix[rowN][0] == 0;
                for (int x = 0; x < matrix[0].length; x++) {
                    if (matrix[0][x] == 0) {
                        matrix[rowN][x] = 0;
                    }
                }
            }
        }

        for (int y = 0; y < matrix.length; y++) {
            if (y != rowN ? matrix[y][0] == 0 : fillRowN) {
                Arrays.fill(matrix[y], 0);
            }
        }
        for (int x = 0; x < matrix[0].length; x++) {
            if (matrix[rowN][x] == 0) {
                for (int y = 0; y < matrix.length; y++) {
                    matrix[y][x] = 0;
                }
            }
        }
    }

    /**
     * Optimal performance
     */
    public void setZeroes2(int[][] matrix) {
        BitSet zeroColumns = new BitSet(matrix[0].length);
        for (int y = 0; y < matrix.length; y++) {
            int[] row = matrix[y];
            boolean zeroRow = false;
            for (int x = 0; x < row.length; x++) {
                if (matrix[y][x] == 0) {
                    if (!zeroRow) {
                        for (int i = 0; i < x; i++) matrix[y][i] = 0; // fill row
                        zeroRow = true;
                    }
                    if (!zeroColumns.get(x)) {
                        for (int i = 0; i < y; i++) matrix[i][x] = 0; // fill column
                        zeroColumns.set(x);
                    }
                } else if (zeroRow || zeroColumns.get(x)) {
                    matrix[y][x] = 0;
                }
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[[0,1,2,7],[3,4,5,2],[1,3,1,5]]:[[0,0,0,0],[0,4,5,2],[0,3,1,5]]",
            "[[9,1,2,7],[3,4,5,2],[1,3,1,5]]:[[9,1,2,7],[3,4,5,2],[1,3,1,5]]",
            "[[9,1,2,7],[3,4,0,2],[1,3,1,5]]:[[9,1,0,7],[0,0,0,0],[1,3,0,5]]",
            "[[0,1,2,0],[3,4,5,2],[1,3,1,5]]:[[0,0,0,0],[0,4,5,0],[0,3,1,0]]",
            "[[0,1,2,0],[3,4,5,2],[0,3,1,0]]:[[0,0,0,0],[0,4,5,0],[0,0,0,0]]",
            "[[1,1,1],[1,0,1],[1,1,1]]:[[1,0,1],[0,0,0],[1,0,1]]",
            "[[1]]:[[1]]",
            "[[0]]:[[0]]",
    }, delimiter = ':')
    public void test1(@ConvertWith(MatrixConverter.class) int[][] in, @ConvertWith(MatrixConverter.class) int[][] out) {
        setZeroes(in);
        assertThat(in, is(out));
    }
}
