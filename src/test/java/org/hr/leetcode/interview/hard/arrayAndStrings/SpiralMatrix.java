package org.hr.leetcode.interview.hard.arrayAndStrings;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        return null;
    }

    @Test
    public void test1() {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        assertThat(spiralOrder(matrix), is(List.of(1,2,3,6,9,8,7,4,5)));
    }
}
