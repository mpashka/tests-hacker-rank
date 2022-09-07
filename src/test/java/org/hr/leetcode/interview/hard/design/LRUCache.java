package org.hr.leetcode.interview.hard.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<Integer, ListNode> cache;
    private final ListNode first;
    private final ListNode last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        last = new ListNode(null, null, null, 0);
        first = new ListNode(null, last, null, 0);
        last.prev = first;
    }

    public int get(int key) {
        ListNode value = cache.get(key);
        if (value == null) {
            return -1;
        }
        value.remove();
        first.append(value);
        return value.value;
    }

    public void put(int key, int value) {
        ListNode oldValue = cache.get(key);
        if (oldValue == null) {
            if (cache.size() == capacity) {
                // evict last key
                cache.remove(last.prev.remove().key);
            }
            oldValue = new ListNode(null, null, key, value);
            cache.put(key, oldValue);
        } else {
            // Move new key to the first position
            oldValue.remove();
            oldValue.value = value;
        }
        // push new key
        first.append(oldValue);
    }

    static class ListNode {
        private ListNode prev;
        private ListNode next;
        private final Integer key;
        private int value;

        public ListNode(ListNode prev, ListNode next, Integer key, int value) {
            this.prev = prev;
            this.next = next;
            this.key = key;
            this.value = value;
        }

        public ListNode remove() {
            prev.next = next;
            next.prev = prev;
            return this;
        }

        public void append(ListNode value) {
            value.next = next;
            value.prev = this;
            next.prev = value;
            next = value;
        }
    }
}
