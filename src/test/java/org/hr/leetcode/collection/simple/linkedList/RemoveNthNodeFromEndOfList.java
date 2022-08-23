package org.hr.leetcode.collection.simple.linkedList;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode[] tail = new ListNode[n+1];
        ListNode next = head;
        int idx = 0;
        while (next != null) {
            tail[idx++ % tail.length] = next;
            next = next.next;
        }
        ListNode remove = tail[(idx-n) % tail.length];
        if (remove == head) {
            return head.next;
        } else {
            ListNode prev = tail[(idx-1-n) % tail.length];
            prev.next = remove.next;
            return head;
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1:1:",
            "1,2,3,4,5:2:1,2,3,5",
            "1,2:1:1",
            "4,5,1,9:3:4,1,9",
            "4,5,1,9:2:4,5,9",
            "4,5,1,9:4:5,1,9",
            "4,5,1,9:1:4,5,1",
            "4:1:",
    }, delimiter = ':')
    public void test1(@ConvertWith(ListNodeConverter.class) ListNode list, int toDelete, @ConvertWith(IntArrayConverter.class) int[] result) {
        assertThat(ListNode.toArray(removeNthFromEnd(list, toDelete)), is(result));
    }
}
