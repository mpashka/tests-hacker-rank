package org.hr.leetcode.interview.simple.strings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.BitSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        BitSet present = new BitSet('z'-'a'+1);
        BitSet nonUnique = new BitSet('z'-'a'+1);
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (nonUnique.get(c)) {
                continue;
            } else if (present.get(c)) {
                nonUnique.set(c);
            } else {
                present.set(c);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (!nonUnique.get(c)) {
                return i;
            }
        }

        return -1;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "leetcode:0",
            "loveleetcode:2",
            "aabb:-1",
    }, delimiter = ':')
    public void test1(String in, int out) {
        assertThat(firstUniqChar(in), is(out));
    }
}
