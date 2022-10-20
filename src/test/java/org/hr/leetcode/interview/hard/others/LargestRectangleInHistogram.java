package org.hr.leetcode.interview.hard.others;

import java.util.Arrays;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int prevHeight = 0, max = 0;
        int[] recs = new int[10_000];
        for (int i = 0; i <= heights.length; i++) {
            int height = i < heights.length ? heights[i] : 0;
            if (height > prevHeight) {
                Arrays.fill(recs, prevHeight, height, i);
            } else if (height < prevHeight) {
                for (int j = height; j < prevHeight; j++) {
                    int rec = (i - recs[j]) * (j+1);
                    max = Math.max(max, rec);
                }
            }
            prevHeight = height;
        }
        return max;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1:1",
            "2,1,5,6,2,3:10",
            "2,4:4",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] height, int result) {
        assertThat(largestRectangleArea(height), is(result));
    }
}
