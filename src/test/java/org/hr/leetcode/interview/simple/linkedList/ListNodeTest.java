package org.hr.leetcode.interview.simple.linkedList;

import org.hr.leetcode.interview.linkedList.ListNode;
import org.hr.leetcode.interview.linkedList.ListNodeConverter;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class ListNodeTest {

    @Test
    public void test1() {
        ListNodeConverter converter = new ListNodeConverter();
        ListNode listNode = converter.convert("1,2,3,4", ListNode.class);
        assertThat(listNode, notNullValue());
        assertThat(listNode.val, is(1));
        assertThat(listNode.next, notNullValue());
        assertThat(listNode.next.val, is(2));
        assertThat(ListNode.toArray(listNode), is(new int[]{1,2,3,4}));
        assertThat(ListNode.toArray(converter.convert("", ListNode.class)), is(new int[0]));
    }
}
