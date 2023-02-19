package org.hr.leetcode.interview.hard.design;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Trie {

    private Set<String> words = new HashSet<>();
    private Set<String> prefixes = new HashSet<>();

    public Trie() {
    }

    public void insert(String word) {
        words.add(word);
        for (int i = 1; i < word.length(); i++) {
            prefixes.add(word.substring(0, i));
        }
    }

    public boolean search(String word) {
        return words.contains(word);
    }

    public boolean startsWith(String prefix) {
        return words.contains(prefix) || prefixes.contains(prefix);
    }

    @Test
    public void test() {
        {
            Trie trie = new Trie();
            trie.insert("apple");
            assertThat(trie.search("apple"), is(true));
            assertThat(trie.search("app"), is(false));
            assertThat(trie.startsWith("app"), is(true));
            trie.insert("app");
            assertThat(trie.search("app"), is(true));
        }
        {
            Trie trie = new Trie();
            trie.insert("a");
            assertThat(trie.search("app"), is(false));
            assertThat(trie.search("a"), is(true));
            assertThat(trie.startsWith("a"), is(true));
        }
    }
}
