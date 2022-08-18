package org.hr.leetcode.collection.simple.strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringToInteger {

    enum State {whitespace, sign, digit, done}
    private static final long MIN_VALUE = (long) Integer.MAX_VALUE + 1;

    public int myAtoi(String s) {
        State state = State.whitespace;
        long number = 0;
        boolean minus = false;
        for (int i = 0; i < s.length() && state != State.done; i++) {
            char c = s.charAt(i);
            switch (state) {
                case whitespace:
                    if (c == ' ') {
                        break;
                    }
                    state = State.sign;
                case sign:
                    if (c == '+') {
                        state = State.digit;
                        break;
                    } else if (c == '-') {
                        state = State.digit;
                        minus = true;
                        break;
                    }
                    state = State.digit;
                case digit:
                    if (!Character.isDigit(c)) {
                        state = State.done;
                        break;
                    }
                    number = number * 10 + (c-'0');
                    if (minus && number >= MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    } else if (!minus && number >= Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
            }
        }
        return (int) (minus ? -number:number);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-2147483647:-2147483647",
            "-2147483646:-2147483646",
            "  -0012a42:-12",
            "42:42",
            "   -42:-42",
            "4193 with words:4193",
            "-2147483648:-2147483648",
            "-2147483649:-2147483648",
            "2147483647:2147483647",
            "2147483648:2147483647",
    }, delimiter = ':')
    public void test1(String s, int val) {
        assertThat(myAtoi(s), is(val));
    }
}
