package org.hr.leetcode.collection.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IntersectionOfTwoArrays2 {


    public int[] intersect(int[] nums1, int[] nums2) {
        final int MAX_VAL = 1001;
        int[] counts1 = new int[MAX_VAL];
        for (int i : nums1) {
            counts1[i]++;
        }
        int length = 0;
        int[] counts = new int[MAX_VAL];
        for (int i : nums2) {
            if (counts[i] < counts1[i]) {
                counts[i]++;
                length++;
            }
        }
        int[] result = new int[length];
        int num = 0;
        for (int i = 0; i < counts.length; i++) {
            int len = counts[i];
            if (len > 0) {
                Arrays.fill(result, num, num+len, i);
                num += len;
            }
        }
        return result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1:1:1",
            "1:2:",
            "1,1:2,2:",
            "1,2:0,2:2",
            "1,2,2,1:2,2:2,2",
            "4,9,5:9,4,9,8,4:4,9",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in1, @ConvertWith(IntArrayConverter.class) int[] in2,
                      @ConvertWith(IntArrayConverter.class) int[] result) {
        int[] intersect = intersect(in1, in2);
        Arrays.sort(intersect);
        Arrays.sort(result);
        assertThat(intersect, is(result));
    }
}
