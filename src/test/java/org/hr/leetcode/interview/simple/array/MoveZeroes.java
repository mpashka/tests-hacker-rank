package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int zeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            } else if (zeros > 0) {
                nums[i-zeros] = nums[i];
            }
        }
        Arrays.fill(nums, nums.length-zeros, nums.length, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,1,0,3,12:1,3,12,0,0",
            "1,2,3,4,5:1,2,3,4,5",
            "1:1",
            ":",
            "0:0",
            "0,0:0,0",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in,
                      @ConvertWith(IntArrayConverter.class) int[] result) {
        moveZeroes(in);
        assertThat(in, is(result));
    }

}
