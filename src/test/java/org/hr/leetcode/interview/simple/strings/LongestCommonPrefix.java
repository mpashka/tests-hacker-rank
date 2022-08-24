package org.hr.leetcode.interview.simple.strings;

import org.hr.utils.StringArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        int len;
        main:
        for (len = 0; len < strs[0].length(); len++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= len || strs[j].charAt(len) != strs[0].charAt(len)) {
                    break main;
                }
            }
        }
        return strs[0].substring(0, len);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "aaa,:",
            "flower,flow,flight:fl",
            "flower:flower",
            "flower,flowera:flower",
            "dog,racecar,car:",
            ",aaa:",
    }, delimiter = ':')
    public void test1(@ConvertWith(StringArrayConverter.class) String[] strs, String prefix) {
        assertThat(longestCommonPrefix(strs), is(prefix == null ? "" : prefix));
    }
}
