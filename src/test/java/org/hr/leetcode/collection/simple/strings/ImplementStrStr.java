package org.hr.leetcode.collection.simple.strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        mainLoop:
        for (int i = 0; i < haystack.length() - needle.length()+1; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    continue mainLoop;
                }
            }
            return i;
        }
        return -1;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a:a:0",
            "aa:a:0",
            "hello:ll:2",
            "aaaaa:bba:-1",
    }, delimiter = ':')
    public void test1(String haystack, String needle, int pos) {
        assertThat(strStr(haystack, needle), is(pos));
    }
}
