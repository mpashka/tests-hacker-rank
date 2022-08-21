package org.hr.leetcode.collection.simple.linkedList;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode next = new ListNode(0, head), prev = next;
        while (next.next.val != n) {
            next = next.next;
        }
        next.next = next.next.next;
        return prev.next;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5:2:1,2,3,5",
            "1:1:",
            "1,2:1:1",
            "4,5,1,9:2:4,1,9",
            "4,5,1,9:3:4,5,9",
            "4,5,1,9:1:5,1,9",
            "4,5,1,9:4:4,5,1",
            "4:1:",
    }, delimiter = ':')
    public void test1(@ConvertWith(ListNodeConverter.class) ListNode list, int toDelete, @ConvertWith(IntArrayConverter.class) int[] result) {
        removeNthFromEnd(list, toDelete);
        assertThat(ListNode.toArray(list), is(result));
    }
}
