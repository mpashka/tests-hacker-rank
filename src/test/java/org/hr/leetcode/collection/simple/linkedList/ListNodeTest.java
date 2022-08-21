package org.hr.leetcode.collection.simple.linkedList;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


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
