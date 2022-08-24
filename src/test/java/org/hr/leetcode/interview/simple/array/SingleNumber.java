package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2,2,1:1",
            "4,1,2,1,2:4",
            "4,-1,2,-1,2:4",
            "-4,1,2,1,2:-4",
            "1:1",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in, int number) {
        assertThat(singleNumber(in), is(number));
    }
}
