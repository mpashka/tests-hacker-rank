package org.hr.leetcode.interview.hard.backtracing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> line = new ArrayList<>();
        int begin = 0, end0 = begin;
        step:
        while (true) {
            for (int end = end0; end < s.length(); end++) {
                if (checkPalindrome(s, begin, end)) {
                    line.add(s.substring(begin, end+1));
                    if (end == s.length()-1) {
                        result.add(new ArrayList<>(line));
                        line.remove(line.size()-1);
                        break;
                    } else {
                        begin = end0 = end+1;
                        continue step;
                    }
                }
            }
            if (line.isEmpty()) {
                break;
            }
            String prev = line.remove(line.size()-1);
            end0 = begin;
            begin -= prev.length();
        }
        return result;
    }

    /**
     * check palindrome
     */
    boolean checkPalindrome(String str, int begin, int end) {
        for (int j = 0; j < (1+end - begin) / 2; j++) {
            if (str.charAt(begin+j) != str.charAt(end - j)) {
                return false;
            }
        }
        return true;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "aba:0:2:true",
            "aba:0:1:false",
            "aba:0:0:true",
            "aba:1:1:true",
            "aba:2:2:true",
            "abbat:0:3:true",
            "abbat:0:2:false",
            "abbat:1:2:true",
    }, delimiter = ':')
    public void testPalindrome(String str, int begin, int end, boolean palindrome) {
        assertThat(checkPalindrome(str, begin, end), is(palindrome));
    }

    @Test
    public void test1() {
        assertThat(new HashSet<>(partition("aab")), is(Set.of(
                List.of("a","a","b"),
                List.of("aa","b")
        )));
    }

    @Test
    public void test2() {
        assertThat(new HashSet<>(partition("a")), is(Set.of(
                List.of("a")
        )));
    }
}
