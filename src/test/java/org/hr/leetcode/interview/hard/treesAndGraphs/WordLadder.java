package org.hr.leetcode.interview.hard.treesAndGraphs;

import org.hr.utils.StringArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Map<NCharString, Set<String>> graph = wordList.stream()
                .flatMap(w -> IntStream.range(0, w.length())
                        .mapToObj(i -> new NCharString(w, i)))
                .collect(Collectors.groupingBy(n -> n, Collectors.mapping(NCharString::getStr, Collectors.toSet())));
        Set<String> visited = new HashSet<>(List.of(beginWord)), prev = visited;
        for (int i = 0; i < wordList.size(); i++) {
            Set<String> next = prev.stream()
                    .flatMap(w -> IntStream.range(0, w.length())
                            .mapToObj(l -> new NCharString(w, l)))
                    .flatMap(n -> {
                        Set<String> nextWords = graph.get(n);
                        return nextWords != null ? nextWords.stream() : Stream.empty();
                    })
                    .filter(w -> !visited.contains(w))
                    .collect(Collectors.toSet());
            if (next.isEmpty()) {
                return 0;
            } else if (next.contains(endWord)) {
                return i+2;
            }
            visited.addAll(next);
            prev = next;
        }
        return 0;
    }

    /**
     * Key without single char
     */
    static class NCharString {
        private String str;
        private int num;

        private int hashCode;

        public NCharString(String str, int num) {
            this.str = str;
            this.num = num;
            for (int i = 0; i < str.length(); i++) {
                if (i != num) {
                    hashCode = 31 * hashCode + (str.charAt(i) & 0xff);
                }
            }
        }

        public String getStr() {
            return str;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NCharString that = (NCharString) o;
            if (num != that.num) {
                return false;
            }
            for (int i = 0; i < str.length(); i++) {
                if (i != num && str.charAt(i) != that.str.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
//            "hit:hit:hit,hot,dot,dog,lot,log,cog:1",
            "hog:cog:cog:2",
            "hit:hot:hot,dot,dog,lot,log,cog:2",
            "hit:dot:hot,dot,dog,lot,log,cog:3",
            "hit:dog:hot,dot,dog,lot,log,cog:4",
            "hit:cog:hot,dot,dog,lot,log,cog:5",
            "hit:cog:hot,dot,dog,lot,log:0",
    }, delimiter = ':')
    public void test1(String begin, String end, @ConvertWith(StringArrayConverter.class) String[] wordList, int length) {
        assertThat(ladderLength(begin, end, Arrays.asList(wordList)), is(length));
    }
}
