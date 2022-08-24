package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int dst = 0;
        int lastSaved = nums[dst++];
        for (int src = 1; src < nums.length; src++) {
            if (lastSaved == nums[src]) {
                continue;
            }
            lastSaved = nums[dst++] = nums[src];
        }
        return dst;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4:1,2,3,4",
            "1,2,2,2,3,4:1,2,3,4",
            "1,1,2,3,4,4:1,2,3,4"
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in, @ConvertWith(IntArrayConverter.class)int[] expect) {
        int len = removeDuplicates(in);
        assertThat(len, is(expect.length));
        assertThat(Arrays.compare(in, 0, len, expect, 0, len), is(0));
    }
}
