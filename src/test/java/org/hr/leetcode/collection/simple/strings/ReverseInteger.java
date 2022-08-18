package org.hr.leetcode.collection.simple.strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReverseInteger {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0; // overflow
        }
        char[] s = Integer.toString(x >= 0 ? x : -x).toCharArray();
        for (int i = 0; i < s.length/2; i++) {
            char c = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = c;
        }
        try {
            int i = Integer.parseInt(new String(s));
            return x >= 0 ? i : -i;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "123:321",
            "-123:-321",
            "120:21",
            "2147483647:0",
            "-2147483648:0",
    }, delimiter = ':')
    public void test1(int in, int out) {
        assertThat(reverse(in), is(out));
    }
}
