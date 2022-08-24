package org.hr.leetcode.interview.simple.linkedList;

import org.hr.leetcode.interview.linkedList.ListNode;
import org.hr.leetcode.interview.linkedList.ListNodeConverter;
import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode next = head, prev = null;
        while (next != null) {
            ListNode newNext = next.next;
            next.next = prev;
            prev = next;
            next = newNext;
        }
        return prev;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5:5,4,3,2,1",
            "1,2:2,1",
            "1:1",
            ":",
    }, delimiter = ':')
    public void test1(@ConvertWith(ListNodeConverter.class) ListNode list, @ConvertWith(IntArrayConverter.class) int[] result) {
        assertThat(ListNode.toArray(reverseList(list)), is(result));
    }
}
