package org.hr.leetcode.simple;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RomanToInteger {

    private static final Map<Character, Integer> vals = new HashMap<>();
    private static final int[] vals0 = {1, 5, 10, 50, 100, 500, 1000};
    private static final char[] symbs0 = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    static {
        for (int i = 0; i < vals0.length; i++) {
            vals.put(symbs0[i], vals0[i]);
        }
    }

    public int romanToInt(String s) {
        int result = 0, prevNum = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int num = vals.get(s.charAt(i));
            result += num >= prevNum ? num : -num;
            prevNum = num;
        }
        return result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3:III",
            "4:IV",
            "9:IX",
            "58:LVIII",
            "1994:MCMXCIV",
    }, delimiter = ':')
    public void test(int num, String text) {
        assertThat(romanToInt(text), is(num));
    }
}
