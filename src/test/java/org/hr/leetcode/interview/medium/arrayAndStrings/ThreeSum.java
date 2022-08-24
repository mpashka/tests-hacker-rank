package org.hr.leetcode.interview.medium.arrayAndStrings;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        SortedMap<Integer, AtomicInteger> numsMap = new TreeMap<>();
        for (int num : nums) {
            numsMap.computeIfAbsent(num, n -> new AtomicInteger()).incrementAndGet();
        }
        List<List<Integer>> result = new ArrayList<>();
        AtomicInteger zero = numsMap.get(0);
        if (zero != null && zero.get() >= 3) {
            result.add(Arrays.asList(0, 0, 0));
        }
        for (Map.Entry<Integer, AtomicInteger> val : numsMap.headMap(0).entrySet()) {
            int num1 = val.getKey();
            Iterator<Integer> num2Iter = numsMap.subMap(num1, -num1*2).keySet().iterator();
            if (val.getValue().get() == 1) {
                num2Iter.next();
            }
            while (num2Iter.hasNext()) {
                int num2 = num2Iter.next();
                int num3 = -num1-num2;
                AtomicInteger count3 = numsMap.get(num3);
                if (count3 != null) {
                    if (num2 == num3 && count3.get() < 2) {
                        continue;
                    } else if (num3 < num2) {
                        continue;
                    }
                    result.add(Arrays.asList(num1, num2, num3));
                }
            }
        }
        return result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1,0,1,2,-1,-4:-1,-1,2,-1,0,1",
            "0,1,1:",
            "0,0,0:0,0,0",
            "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0:0,0,0",
            "1,1,-1,-1,0,0,0,0,0,0,0,0,0,0,0:0,0,0,-1,1,0",
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] nums, @ConvertWith(IntArrayConverter.class) int[] result) {
        List<List<Integer>> calcList = threeSum(nums);
        Set<List<Integer>> calc = calcList
                .stream()
                .map(l -> {
                    List<Integer> list = new ArrayList<>(l);
                    list.sort(Integer::compareTo);
                    return list;
                })
                .collect(Collectors.toSet());
        assertThat(calc.size(), is(calcList.size()));

        Set<List<Integer>> res = new HashSet<>();
        List<Integer> resIn = Arrays.stream(result).boxed().collect(Collectors.toList());
        for (int i = 0; i < result.length; i += 3) {
            List<Integer> list = new ArrayList<>(resIn.subList(i, i + 3));
            list.sort(Integer::compareTo);
            res.add(list);
        }
        assertThat(calc, is(res));
    }
}
