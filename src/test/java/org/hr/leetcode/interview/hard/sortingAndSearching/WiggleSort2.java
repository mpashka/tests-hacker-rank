package org.hr.leetcode.interview.hard.sortingAndSearching;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WiggleSort2 {
    public void wiggleSort(int[] nums) {

    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,5,1,1,6,4:1,6,1,5,1,4",
            "1,3,2,2,3,1:2,3,1,3,1,2",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] nums, @ConvertWith(IntArrayConverter.class) int[] res) {
        wiggleSort(nums);
        assertThat(nums, is(res));
    }
}
