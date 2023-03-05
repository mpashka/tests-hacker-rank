package org.hr.leetcode.interview.hard.design;

import java.util.NavigableSet;
import java.util.TreeSet;

public class FindMedianFromDataStream {
    private final NavigableSet<Value> data = new TreeSet<>();
    private Value begin, end;

    public void addNum(int num) {
        Value value = new Value(num, data.size());
        data.add(value);
        if (begin == null) {
            begin = value;
        } else {
            if (end == null) {
                // раньше было нечётное число элементов, надо сдвинуться
                int compare = value.compareTo(begin);
                if (compare < 0) {
                    end = begin;
                    begin = prev(begin);
                } else if (compare > 0) {
                    end = next(begin);
                } else {
                    throw new RuntimeException("Must not be 0 here");
                }
            } else {
                if (value.compareTo(begin) < 0) {
                    // end = null
                } else if (value.compareTo(end) > 0) {
                    begin = end;
                } else {
                    begin = value;
                }
                end = null;
            }
        }
    }

    private Value prev(Value value) {
        return data.headSet(value, false).descendingIterator().next();
    }

    private Value next(Value value) {
        return data.tailSet(value, false).iterator().next();
    }

    public double findMedian() {
        return end == null ? begin.val : (begin.val + end.val) / 2.;
    }

    static class Value implements Comparable<Value> {
        private final int val;
        private final int count;

        public Value(int val, int count) {
            this.val = val;
            this.count = count;
        }

        @Override
        public int compareTo(FindMedianFromDataStream.Value o) {
            return count == o.count ? 0 :
                    (val == o.val ? Integer.compare(count, o.count) : Integer.compare(val, o.val));
        }
    }
}
