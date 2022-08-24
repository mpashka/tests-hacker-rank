package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int[] result = new int[digits.length];
        System.arraycopy(digits, 0, result, 0, digits.length);
        for (int i = 0; i < result.length; i++) {
            int pos = result.length - i - 1;
            int num = result[pos] + 1;
            if (num <= 9) {
                result[pos] = num;
                return result;
            } else {
                result[pos] = 0;
            }
        }
        int[] result1 = new int[result.length+1];
        System.arraycopy(result, 0, result1, 1, result.length);
        result1[0] = 1;
        return result1;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3:1,2,4",
            "4,3,2,1:4,3,2,2",
            "9:1,0",
            "4,3,2,9:4,3,3,0",
            "4,3,9,9:4,4,0,0",
            "4,9,9,9:5,0,0,0",
            "9,9,9,9:1,0,0,0,0",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] in, @ConvertWith(IntArrayConverter.class) int[] result) {
        assertThat(plusOne(in), is(result));
    }
}
