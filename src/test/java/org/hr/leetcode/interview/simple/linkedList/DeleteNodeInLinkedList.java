package org.hr.leetcode.interview.simple.linkedList;

import org.hr.leetcode.interview.linkedList.ListNode;
import org.hr.leetcode.interview.linkedList.ListNodeConverter;
import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeleteNodeInLinkedList {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "4,5,1,9:5:4,1,9",
            "4,5,1,9:1:4,5,9",
            "4,5,1,9:4:5,1,9",
    }, delimiter = ':')
    public void test1(@ConvertWith(ListNodeConverter.class) ListNode list, int toDelete, @ConvertWith(IntArrayConverter.class) int[] result) {
        deleteNode(ListNode.find(list, toDelete));
        assertThat(ListNode.toArray(list), is(result));
    }
}
