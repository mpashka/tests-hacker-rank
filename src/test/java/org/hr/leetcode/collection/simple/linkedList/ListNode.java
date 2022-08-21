package org.hr.leetcode.collection.simple.linkedList;

import java.util.Objects;
import java.util.stream.Stream;

public class ListNode {
    private static final int[] EMPTY_ARRAY = new int[0];

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(int x) {
        val = x;
    }

    public static int[] toArray(ListNode node) {
        return node != null
                ? Stream.iterate(node, Objects::nonNull, n -> n.next)
                .mapToInt(n -> n.val)
                .toArray()
                : EMPTY_ARRAY;
    }

    public static ListNode find(ListNode node, int val) {
        return Stream.iterate(node, Objects::nonNull, n -> n.next)
                .filter(n -> n.val == val)
                .findAny()
                .orElse(null);
    }

    public static ListNode append(ListNode p, ListNode n) {
        if (p == null) {
            return n;
        } else {
            ListNode prev = p;
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = n;
            return p;
        }
    }

    public static ListNode append(ListNode n, int val) {
        return ListNode.append(n, new ListNode(val));
    }
}
