package org.hr.leetcode.interview.hard.arrayAndStrings;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int mult = 1;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length-1; i++) {
            mult *= nums[i];
            result[i] = mult;
        }
        mult = 1;
        for (int i = nums.length-1; i >= 1; i--) {
            result[i] = mult * result[i-1];
            mult *= nums[i];
        }
        result[0] = mult;
        return result;
    }

    public int[] productExceptSelf_div(int[] nums) {
        int zeroPos = -1;
        long multi = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                if (zeroPos == -1) {
                    zeroPos = i;
                } else {
                    return new int[nums.length];
                }
            } else {
                multi *= num;
            }
        }
        int[] result = new int[nums.length];
        if (zeroPos != -1) {
            result[zeroPos] = (int) multi;
        } else {
            for (int i = 0; i < nums.length; i++) {
                result[i] = (int) (multi / nums[i]);
            }
        }
        return result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4:24,12,8,6",
            "-1,1,0,-3,3:0,0,9,0,0",
            "-1,1,0,-3,0:0,0,0,0,0",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] nums, @ConvertWith(IntArrayConverter.class) int[] result) {
        assertThat(productExceptSelf(nums), is(result));
    }
}
