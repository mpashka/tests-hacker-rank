package org.hr.interview.company3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

public class GetTop<T extends Comparable> {

    private static final Comparator<Integer> compareTo = Integer::compareTo;
    private static final Comparator<Integer> compareToReversed = compareTo.reversed();

    private final int topSize;
    private final NavigableSet<T> top;

    public GetTop(int topSize, Comparator<T> comparator) {
        this.topSize = topSize;
        this.top = new TreeSet<>(comparator);
    }

    public synchronized void push(T val) {
        int prevSize = top.size();
        top.add(val);
        if (top.size() > topSize) {
            top.pollLast();
        }
        if (prevSize < topSize && top.size() >= topSize) {
            notifyAll();
        }
    }

    public synchronized Collection<T> top() throws InterruptedException {
        while (top.size() < topSize) {
            wait();
        }

        while (top.size() > topSize) {
            top.pollLast();
        }
        return new ArrayList<>(top);
    }


    public static class TestTop {

        @Test
        public void test1() throws InterruptedException {
            GetTop<Integer> topSelector = new GetTop<>(3, compareToReversed);
            assertThat(topSelector.top(), empty());

            topSelector.push(1);
            assertThat(topSelector.top(), contains(1));

            topSelector.push(2);
            assertThat(topSelector.top(), contains(2, 1));

            topSelector.push(2);
            assertThat(topSelector.top(), contains(2, 1));

            topSelector.push(3);
            assertThat(topSelector.top(), contains(3, 2, 1));

            topSelector.push(1);
            assertThat(topSelector.top(), contains(3, 2, 1));

            topSelector.push(4);
            assertThat(topSelector.top(), contains(4, 3, 2));

            topSelector.push(4);
            assertThat(topSelector.top(), contains(4, 3, 2));

            topSelector.push(3);
            assertThat(topSelector.top(), contains(4, 3, 2));
        }
    }
}
