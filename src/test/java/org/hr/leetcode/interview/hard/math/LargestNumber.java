package org.hr.leetcode.interview.hard.math;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String result = Arrays.stream(nums).mapToObj(String::valueOf)
                .sorted(new StringNumComparator().reversed())
                .collect(Collectors.joining());
        return IntStream.range(0, result.length()).map(result::charAt).filter(c -> c != '0').findAny().isEmpty()
                ? "0" : result;
    }

    static class StringNumComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int o1l = o1.length(), o2l = o2.length();
            for (int i = 0; i < o1l+o2l; i++) {
                char c1 = i < o1l ? o1.charAt(i) : o2.charAt(i - o1l);
                char c2 = i < o2l ? o2.charAt(i) : o1.charAt(i - o2l);
                if (c1 < c2) {
                    return -1;
                } else if (c1 > c2) {
                    return 1;
                }
            }
            return 0;
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "543:5435:-1",
            "5435:543:1",
            "543:543:0",
            "543:543543:0",
    }, delimiter = ':')
    public void testComparator(String s1, String s2, int result) {
        assertThat(new StringNumComparator().compare(s1, s2), is(result));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10,2:210",
            "8,6,67:8676",
            "3,30,34,5,9:9534330",
            "3,30,34,5,9,92:992534330",
            "3,30,34,5,9,96:996534330",
            "0:0",
            "0,0:0",
            "0,0,0,0:0",
    }, delimiter = ':')
    public void testLargestNumber(@ConvertWith(IntArrayConverter.class) int[] nums, String result) {
        assertThat(largestNumber(nums), is(result));
    }

}
