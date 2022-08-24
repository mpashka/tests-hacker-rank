package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.BitSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContainsDuplicate {
    public boolean containsDuplicateLong(int[] nums) {
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        BitSet posValues = new BitSet();
        BitSet negValues = new BitSet();
        for (int num : nums) {
            BitSet values = num >= 0 ? posValues : negValues;
            int val = num >= 0 ? num : -num;
            if (values.get(val)) {
                return true;
            }
            values.set(val);
        }
        return false;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,1:true",
            "0,2,3,1:false",
            "0,2,3,0:true",
            "-1,-2,0,2,3,0:true",
            "-1,-2,2,3,0:false",
            "-1,-2,2,3,-1:true",
            "-1,-2,2,3,-1,2,3:true",
            "1,2,3,4:false",
            "1,1,1,3,3,4,3,2,4,2:true",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in, boolean duplicates) {
        assertThat(containsDuplicate(in), is(duplicates));
    }
}
