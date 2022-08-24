package org.hr.leetcode.interview.simple.strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReverseString {
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length/2; i++) {
            char c = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = c;
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
//            ":",
            "h:h",
            "hell:lleh",
            "hello:olleh",
            "Hannah:hannaH",
    }, delimiter = ':')
    public void test1(String in, String out) {
        char[] s = in.toCharArray();
        reverseString(s);
        assertThat(s, is(out.toCharArray()));
    }
}
