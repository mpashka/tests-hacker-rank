package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (k % nums.length == 0 || nums.length <= 1) return;
        int start = 0;
        int nextPos = 0;
        int next = nums[nextPos];
        for (int i = 0; i < nums.length; i++) {
            nextPos = (nextPos + k) % nums.length;
            int prev = nums[nextPos];
            nums[nextPos] = next;
            if (nextPos == start) {
                nextPos = ++start;
                next = nums[nextPos];
            } else {
                next = prev;
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6,7:3:5,6,7,1,2,3,4",
            "1,2,3,4,5,6,7:1:7,1,2,3,4,5,6",
            "1,2,3,4,5,6,7:2:6,7,1,2,3,4,5",
            "-1,-100,3,99:1:99,-1,-100,3",
            "-1,-100,3,99:2:3,99,-1,-100",
            "1,2:1:2,1",
            "1,2,3:1:3,1,2",
            "1,2,3,4:1:4,1,2,3",
            "1,2,3,4:0:1,2,3,4",
            "1:0:1",
            "1:1:1",
            "1,2:2:1,2",
            "1,2:3:2,1",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in, int k, @ConvertWith(IntArrayConverter.class) int[] expect) {
        rotate(in, k);
        assertThat(in, is(expect));
    }
}
