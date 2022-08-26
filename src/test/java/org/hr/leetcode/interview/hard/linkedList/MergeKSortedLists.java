package org.hr.leetcode.interview.hard.linkedList;

import org.hr.utils.linkedList.ListNode;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null, tail = null;
        while (true) {
            int minIdx = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && (minIdx == -1 || lists[i].val < lists[minIdx].val)) {
                    minIdx = i;
                }
            }
            if (minIdx == -1) {
                return head;
            }
            ListNode next = lists[minIdx];
            if (tail != null) {
                tail.next = next;
            } else {
                head = next;
            }
            lists[minIdx] = next.next;
            tail = next;
            tail.next = null;
        }
    }

    @Test
    public void test1() {
        ListNode[] lists = {
                ListNode.fromArray(1,4,5),
                ListNode.fromArray(1,3,4),
                ListNode.fromArray(2,6)
        };
        assertThat(ListNode.toArray(mergeKLists(lists)), is(new int[]{
                1,1,2,3,4,4,5,6
        }));
    }

    @Test
    public void test2() {
        ListNode[] lists = {};
        assertThat(ListNode.toArray(mergeKLists(lists)), is(new int[0]));
    }

    @Test
    public void test3() {
        ListNode[] lists = {null};
        assertThat(ListNode.toArray(mergeKLists(lists)), is(new int[0]));
    }
}
