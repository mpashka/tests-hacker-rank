package org.hr.leetcode.interview.hard.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    private Deque<Iterator<NestedInteger>> iteratorStack = new LinkedList<>();
    private Integer next;

    public NestedIterator(List<NestedInteger> nestedList) {
        iteratorStack.addFirst(nestedList.iterator());
        next();
    }

    @Override
    public Integer next() {
        Integer nextReturn = this.next;
        while (true) {
            Iterator<NestedInteger> iterator = iteratorStack.peekFirst();
            if (iterator.hasNext()) {
                NestedInteger next = iterator.next();
                if (next.isInteger()) {
                    this.next = next.getInteger();
                    break;
                } else {
                    iteratorStack.addFirst(next.getList().iterator());
                }
            } else if (iteratorStack.size() == 1) {
                next = null;
                break;
            } else {
                iteratorStack.removeFirst();
            }
        }
        return nextReturn;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }


    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    static class Value implements NestedInteger {
        private int value;

        public Value(int value) {
            this.value = value;
        }

        @Override
        public boolean isInteger() {
            return true;
        }

        @Override
        public Integer getInteger() {
            return value;
        }

        @Override
        public List<NestedInteger> getList() {
            return null;
        }
    }

    static class NestedList implements NestedInteger {
        private List<NestedInteger> list;

        public NestedList(NestedInteger... list) {
            this.list = Arrays.stream(list).collect(Collectors.toList());
        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }
}
