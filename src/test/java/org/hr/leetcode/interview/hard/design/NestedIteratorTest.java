package org.hr.leetcode.interview.hard.design;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NestedIteratorTest {
    private NestedIterator.NestedInteger list(int... values) {
        return new NestedIterator.NestedList(Arrays.stream(values).mapToObj(NestedIterator.Value::new).toArray(NestedIterator.NestedInteger[]::new));
    }

    private int[] intArr(NestedIterator.NestedInteger... nestedInteger) {
        Iterator<Integer> iterator = new NestedIterator(Arrays.asList(nestedInteger));
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .mapToInt(i -> i)
                .toArray();
    }

    public NestedIterator.NestedInteger flat(List<NestedIterator.NestedInteger>... list) {
        return new NestedIterator.NestedList(Arrays.stream(list).flatMap(Collection::stream)
                .toArray(NestedIterator.NestedInteger[]::new));
    }

    @Test
    public void test() {
        assertThat(intArr(list(1,2,3)), is(new int[]{1,2,3}));
        assertThat(intArr(new NestedIterator.NestedList(list(1,2,3))), is(new int[]{1,2,3}));
        assertThat(intArr(new NestedIterator.NestedList(list(1,2,3), list(4,5,6,7))), is(new int[]{1,2,3,4,5,6,7}));
        assertThat(intArr(flat(list(1,2,3).getList(), List.of(list(4,5,6,7)))), is(new int[]{1,2,3,4,5,6,7}));

        assertThat(intArr(new NestedIterator.Value(1), list(2,3,4),
                flat(list(5, 6, 7).getList(), new NestedIterator.NestedList(list(8, 9, 10), list(11,12,13)).getList()),
                list(14,15,16)
        ), is(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}));
    }
}
