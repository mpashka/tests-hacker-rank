package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> pos = new HashMap<>();
        int halfTarget = target % 2 == 0 ? target / 2 : Integer.MAX_VALUE;
        Set<Integer> halfPos = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            pos.put(nums[i], i);
            if (nums[i] == halfTarget) {
                halfPos.add(i);
            }
        }
        if (halfPos.size() == 2) {
            return halfPos.stream().mapToInt(p -> p).toArray();
        }

        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            Integer otherPos = pos.get(other);
            if (otherPos != null && otherPos != i) {
                return new int[]{i, otherPos};
            }
        }
        return null;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2,7,11,15:9:0,1",
            "3,2,4:6:1,2",
            "3,3:6:0,1"
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] in, int target, @ConvertWith(IntArrayConverter.class) int[] result) {
        assertThat(twoSum(in, target), is(result));
    }
}
