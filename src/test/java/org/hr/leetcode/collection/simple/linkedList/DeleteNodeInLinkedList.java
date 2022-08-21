package org.hr.leetcode.collection.simple.linkedList;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

public class DeleteNodeInLinkedList {
    public void deleteNode(ListNode node) {

    }

    @ParameterizedTest
    @CsvSource(value = {
            "7,1,5,3,6,4:7",
            "7,6,5,4,3,2,1,2,3,4,5,3,4,5,6,5,4:7",
            "1,2,3,4,5:4",
            "7,6,4,3,1:0",
            "7:0",
            "1,7:6",
            "1,7,1:6",
            "1,7,1,8:13",
            "1,7,1,6:11",
            "7,4:0"
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] prices, int profit) {

    }
}
