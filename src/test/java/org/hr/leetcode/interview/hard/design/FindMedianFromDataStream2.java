package org.hr.leetcode.interview.hard.design;

import java.util.SortedSet;
import java.util.TreeSet;

public class FindMedianFromDataStream2 {
    private SortedSet<Value> data = new TreeSet<>();

    public void addNum(int num) {
        data.add(new Value(num, data.size()));
    }

    public double findMedian() {
        int begin, size;
        if (data.size() % 2 == 0) {
            begin = data.size() / 2 - 1;
            size = 2;
        } else {
            begin = data.size() / 2;
            size = 1;
        }

/*
        System.out.println("arr: " +
                Arrays.toString(data.stream()
                .mapToInt(i -> i.val)
                .toArray()));

*/
        return data.stream()
                .mapToInt(i -> i.val)
                .skip(begin)
                .limit(size)
                .average()
                .orElseThrow();
    }

    static class Value implements Comparable<Value> {
        private int val;
        private int count;

        public Value(int val, int count) {
            this.val = val;
            this.count = count;
        }

        @Override
        public int compareTo(Value o) {
            return count == o.count ? 0 :
                    (val == o.val ? count - o.count : Integer.compare(val, o.val));
        }
    }
}
