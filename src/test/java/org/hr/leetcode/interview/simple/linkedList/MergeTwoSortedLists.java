package org.hr.leetcode.interview.simple.linkedList;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list = new ListNode(), last = list;
        AtomicReference<ListNode> next, list1Ref = new AtomicReference<>(list1), list2Ref = new AtomicReference<>(list2);
        while (list1Ref.get() != null || list2Ref.get() != null) {
            if (list1Ref.get() == null) {
                next = list2Ref;
            } else if (list2Ref.get() == null) {
                next = list1Ref;
            } else {
                next = list1Ref.get().val < list2Ref.get().val ? list1Ref : list2Ref;
            }
            last = last.next = next.get();
            next.set(next.get().next);
        }
        return list.next;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,4:1,3,4:1,1,2,3,4,4",
            "::",
            ":0:0",
            "1::1",
    }, delimiter = ':')
    public void test1(@ConvertWith(ListNodeConverter.class) ListNode list1,
                      @ConvertWith(ListNodeConverter.class) ListNode list2,
                      @ConvertWith(IntArrayConverter.class) int[] result) {
        assertThat(ListNode.toArray(mergeTwoLists(list1, list2)), is(result));
    }
}
