package org.hr.leetcode.interview.linkedList;

import java.util.Arrays;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListNode {
    private static final int[] EMPTY_ARRAY = new int[0];

    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "{" + val + '}';
    }


    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\s*(\\d+)\\s*,?");
    public static ListNode fromString(String str) {
        return fromIterator(NUMBER_PATTERN.matcher(str)
                .results()
                .map(r -> r.group(1))
                .mapToInt(Integer::parseInt)
                .iterator());
    }

    public static ListNode fromArray(int... arr) {
        return fromIterator(Arrays.stream(arr).iterator());
    }

    public static ListNode fromIterator(PrimitiveIterator.OfInt in) {
        if (!in.hasNext()) {
            return null;
        }
        ListNode first = new ListNode(in.nextInt()), last = first;
        while (in.hasNext()) {
            last = last.next = new ListNode(in.nextInt());
        }
        return first;
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
