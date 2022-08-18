package org.hr.leetcode.collection.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.BitSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        BitSet row = new BitSet(9);
        BitSet column = new BitSet(9);
        BitSet square = new BitSet(9);
        for (int i = 0; i < 9; i++) {
            row.clear();
            column.clear();
            square.clear();
            for (int j = 0; j < 9; j++) {
                if (!valid(board, row, j, i) || !valid(board, column, i, j)
                    || !validSquare(board, square, i, j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validSquare(char[][] board, BitSet bitSet, int square, int pos) {
        int sx = square % 3;
        int sy = square / 3;
        int px = pos % 3;
        int py = pos / 3;
        return valid(board, bitSet, sx*3 + px, sy*3 + py);
    }

    private boolean valid(char[][] board, BitSet bitSet, int x, int y) {
        char c = board[y][x];
        if (c == '.') return true;
        int num = c - '0';
        if (bitSet.get(num)) {
            return false;
        }
        bitSet.set(num);
        return true;
    }

    @Test
    public void test1() {
        char[][] board1 =
{{'5','3','.','.','7','.','.','.','.'}
,{'6','.','.','1','9','5','.','.','.'}
,{'.','9','8','.','.','.','.','6','.'}
,{'8','.','.','.','6','.','.','.','3'}
,{'4','.','.','8','.','3','.','.','1'}
,{'7','.','.','.','2','.','.','.','6'}
,{'.','6','.','.','.','.','2','8','.'}
,{'.','.','.','4','1','9','.','.','5'}
,{'.','.','.','.','8','.','.','7','9'}};
        assertThat(isValidSudoku(board1), is(true));

        char[][] board2 = 
{{'8','3','.','.','7','.','.','.','.'}
,{'6','.','.','1','9','5','.','.','.'}
,{'.','9','8','.','.','.','.','6','.'}
,{'8','.','.','.','6','.','.','.','3'}
,{'4','.','.','8','.','3','.','.','1'}
,{'7','.','.','.','2','.','.','.','6'}
,{'.','6','.','.','.','.','2','8','.'}
,{'.','.','.','4','1','9','.','.','5'}
,{'.','.','.','.','8','.','.','7','9'}};
        assertThat(isValidSudoku(board2), is(false));

    }
}
