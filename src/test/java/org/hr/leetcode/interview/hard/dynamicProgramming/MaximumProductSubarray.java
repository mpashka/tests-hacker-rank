package org.hr.leetcode.interview.hard.dynamicProgramming;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int begin = Integer.MIN_VALUE, end = Integer.MIN_VALUE, total = Integer.MIN_VALUE, max = nums[0];
        boolean isEnd = false;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                max = IntStream.of(0, max, begin, end, total).max().getAsInt();
                begin = end = total = Integer.MIN_VALUE;
                isEnd = false;
                continue;
            }
            if (num < 0 && total > 0) {
                begin = total;
            }
            if (isEnd) {
                end = end == Integer.MIN_VALUE ? num : end * num;
            }
            total = total == Integer.MIN_VALUE ? num : total * num;
            if (num < 0 && end == Integer.MIN_VALUE) {
                isEnd = true;
            }
        }
        return IntStream.of(max, begin, end, total).max().getAsInt();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-2:-2",
            "0,2,-3,4,-5,6,-7,8:6720",
            "2,3,-2,4:6",
            "1,2,3,4:24",
            "1,2,0,4:4",
            "0,3,2,0,4:6",
            "0,2,-3,4,5,6,-7,8:40320",
            "-2,0,-1:0",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] nums, int result) {
        assertThat(maxProduct(nums), is(result));
    }
}
