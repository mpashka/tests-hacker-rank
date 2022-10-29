package org.hr.leetcode.interview.hard.others;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class IntegerToRoman {
    private static final Logger log = LogManager.getLogger();

    public String intToRoman1(int num) {
        int[] vals0 = {1, 5, 10, 50, 100, 500, 1000};
        char[] symbs0 = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] vals = new int[vals0.length + 6];
        String[] symbs = new String[vals.length];
        int minusPos = -1, nextMinusVal = 1, idx = 0;
        for (int i = 0; i < vals0.length; i++) {
            if (minusPos >= 0) {
                vals[idx] = vals0[i] - vals0[minusPos];
                symbs[idx] = "" + symbs0[minusPos] + symbs0[i];
                idx++;
            }
            vals[idx] = vals0[i];
            symbs[idx] = "" + symbs0[i];
            idx++;
            if (vals0[i] == nextMinusVal) {
                minusPos = i;
                nextMinusVal *= 10;
            }
        }
        StringBuilder result = new StringBuilder();
        int rest = num, valIdx = vals.length - 1;
        while (rest > 0) {
            while (vals[valIdx] > rest) {
                valIdx--;
            }
            result.append(symbs[valIdx]);
            rest -= vals[valIdx];
        }
        return result.toString();
    }

    private static final int[] vals = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static final String[] symbs = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int rest = num, valIdx = vals.length - 1;
        while (rest > 0) {
            while (vals[valIdx] > rest) {
                valIdx--;
            }
            result.append(symbs[valIdx]);
            rest -= vals[valIdx];
        }
        return result.toString();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "3:III",
            "58:LVIII",
            "1994:MCMXCIV",
    }, delimiter = ':')
    public void test(int num, String text) {
        assertThat(intToRoman(num), is(text));
    }
}
