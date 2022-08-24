package org.hr.leetcode.interview.simple.strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidAnagram {
    public boolean isAnagram(String in, String test) {
        Map<Integer, AtomicInteger> count = new HashMap<>();
        for (int i = 0; i < in.length(); i++) {
            int s = in.charAt(i) - 'a';
            count.computeIfAbsent(s, s1 -> new AtomicInteger()).incrementAndGet();
        }
        for (int i = 0; i < test.length(); i++) {
            int s = test.charAt(i) - 'a';
            AtomicInteger c = count.get(s);
            if (c == null) {
                return false;
            }
            if (c.decrementAndGet() == 0) {
                count.remove(s);
            }
        }
        return count.isEmpty();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "anagram:nagaram:true",
            "rat:car:false",
    }, delimiter = ':')
    public void test1(String s, String t, boolean valid) {
        assertThat(isAnagram(s, t), is(valid));
    }
}
