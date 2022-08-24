package org.hr.leetcode.interview.medium.arrayAndStrings;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(s -> IntStream.range(0, s.length())
                        .mapToObj(s::charAt)
                        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                ))
                .values());
    }

    @Test
    public void test1() {
        testGroupAnagram(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                new String[][]{{"bat"}, {"nat","tan"},{"ate","eat","tea"}});
        testGroupAnagram(new String[]{""},
                new String[][]{{""}});
        testGroupAnagram(new String[]{"a"},
                new String[][]{{"a"}});
    }

    private void testGroupAnagram(String[] in, String[][] expected) {
        List<List<String>> res = groupAnagrams(in);
        Set<Set<String>> resSet = res.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());
        Set<Set<String>> expectedSet = Arrays.stream(expected)
                .map(a -> Arrays.stream(a).collect(Collectors.toSet()))
                .collect(Collectors.toSet());
        assertThat(resSet, is(expectedSet));
    }
}
