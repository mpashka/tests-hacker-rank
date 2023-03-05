package org.hr.leetcode.interview.hard.design;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class FindMedianFromDataStreamTest {

    @Test
    public void test1() {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(1);    // arr = [1]
        assertThat(medianFinder.findMedian(), closeTo(1, 0.01));
        medianFinder.addNum(2);    // arr = [1, 2]
        assertThat(medianFinder.findMedian(), closeTo(1.5, 0.01));
        medianFinder.addNum(3);    // arr[1, 2, 3]
        assertThat(medianFinder.findMedian(), closeTo(2, 0.01));
    }

    @Test
    public void test2() {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(10);    // arr = [1]
        assertThat(medianFinder.findMedian(), closeTo(10, 0.01));
        medianFinder.addNum(20);    // arr = [1, 2]
        assertThat(medianFinder.findMedian(), closeTo(15, 0.01));
        medianFinder.addNum(30);    // arr[1, 2, 3]
        assertThat(medianFinder.findMedian(), closeTo(20, 0.01));
    }

    @Test
    public void test3() {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), closeTo(6, 0.01));
        medianFinder.addNum(10);
        assertThat(medianFinder.findMedian(), closeTo(8, 0.01));
        medianFinder.addNum(2);
        assertThat(medianFinder.findMedian(), closeTo(6, 0.01));
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), closeTo(6, 0.01));
        medianFinder.addNum(5);
        assertThat(medianFinder.findMedian(), closeTo(6, 0.01));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), closeTo(5.5, 0.01));
        medianFinder.addNum(6);
        assertThat(medianFinder.findMedian(), closeTo(6, 0.01));
        medianFinder.addNum(3);
        assertThat(medianFinder.findMedian(), closeTo(5.5, 0.01));
        medianFinder.addNum(1);
        assertThat(medianFinder.findMedian(), closeTo(5, 0.01));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), closeTo(4, 0.01));
        medianFinder.addNum(0);
        assertThat(medianFinder.findMedian(), closeTo(3, 0.01));
    }
}
