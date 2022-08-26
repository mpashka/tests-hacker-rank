package org.hr.leetcode.interview.hard.backtracing;

import org.hr.utils.StringArrayConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {

    }

    @Test
    public void test1() {
        assertThat(partition("aab"), is(List.of(
                List.of("a","a","b"),
                List.of("aa","b")
        )));
    }

    @Test
    public void test2() {
        assertThat(partition("a"), is(List.of(
                List.of("a")
        )));
    }
}
