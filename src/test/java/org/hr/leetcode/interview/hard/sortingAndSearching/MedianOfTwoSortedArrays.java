package org.hr.leetcode.interview.hard.sortingAndSearching;

import java.util.Arrays;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] pos = new int[2];
        int[][] nums = {nums1, nums2};

        int[] res = new int[1 + (nums1.length+nums2.length+1) % 2];
        int len = 1+(nums1.length+nums2.length)/2;
        for (int i = 0; i < len; i++) {
            int minRow = -1;
            for (int j = 0; j < nums.length; j++) {
                if (pos[j] < nums[j].length && (minRow == -1 || nums[j][pos[j]] < nums[minRow][pos[minRow]])) {
                    minRow = j;
                }
            }
            res[i % res.length] = nums[minRow][pos[minRow]];
            pos[minRow]++;
        }
        return (double) Arrays.stream(res).sum() / res.length;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,3:2:2",
            "1,2:3,4:2.5",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] nums1, @ConvertWith(IntArrayConverter.class) int[] nums2, double result) {
        assertThat(findMedianSortedArrays(nums1, nums2), closeTo(result, 0.01));
    }
}
