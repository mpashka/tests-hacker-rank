package org.hr.interview.vk;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> intervals;

        // даны интервалы [начало; конец] на одной оси
        // нужно склеить пересекающиеся, чтобы не было наложений
        // непересекающиеся оставить, как есть
        //
        // in =  [ [1,5], [3,7] ], out = [ [1,7] ]
        //
        // 0 1 2 3 4 5 6 7 8 9 ...
        // in:
        // --[-------]----------------------
        // ------[-------]------------------
        // out:
        // --[-----------]------------------
        intervals = List.of(new Interval(1, 5), new Interval(3, 7));
        System.out.println(mergeIntervals(intervals));

        // in ->  [[8,10], [1,3], [15,18], [2,6], [9,11]]
        // out -> [[1,6], [8,11], [15,18]]
        intervals = List.of(ivl(8, 10), ivl(1, 3), ivl(15, 18), ivl(2, 6), ivl(9, 11));
        System.out.println(mergeIntervals(intervals));

        // in ->  [[1, 10], [2, 5], [4, 7]]
        // out -> [[1, 10]]
        intervals = List.of(ivl(1, 10), ivl(2, 5), ivl(4, 7));
        System.out.println(mergeIntervals(intervals));

        // in ->  [ ]
        // out -> [ ]
        intervals = List.of();
        System.out.println(mergeIntervals(intervals));

        // in ->  [[6, 10], [1, 4], [3, 7]]
        // out -> [[1, 10]]
        intervals = List.of(ivl(6, 10), ivl(1, 4), ivl(3, 7));
        System.out.println(mergeIntervals(intervals));

        // in ->  [[1, 5], [2, 3], [4, 6]]
        // out -> [[1, 6]]
        intervals = List.of(ivl(1, 5), ivl(2, 3), ivl(4, 6));
        System.out.println(mergeIntervals(intervals));

        // in ->  [[1, 3], [2, 5], [4, 6]]
        // out -> [[1, 6]]
        intervals = List.of(ivl(1, 3), ivl(2, 5), ivl(4, 6));
        System.out.println(mergeIntervals(intervals));
    }

    static List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Edge> edges = new ArrayList<>(intervals.size()*2);
        for (Interval interval : intervals) {
            edges.add(new Edge(interval.begin, true));
            edges.add(new Edge(interval.end, false));
        }
        edges.sort((e1, e2) -> {
            int compare = Integer.compare(e1.point, e2.point);
            if (compare != 0) {
                return compare;
            }
            return Boolean.compare(e2.begin, e1.begin);
        });
        int count = 0;
        List<Interval> result = new ArrayList<>(intervals.size());
        int begin = 0;
        for (Edge edge : edges) {
            if (edge.begin) {
                if (count == 0) {
                    begin = edge.point;
                }
                count++;
            } else {
                count--;
                if (count == 0) {
                    result.add(new Interval(begin, edge.point));
                }
            }
        }
        return result;
    }

    static class Edge {
        int point;
        boolean begin;

        public Edge(int point, boolean begin) {
            this.point = point;
            this.begin = begin;
        }
    }

    static Interval ivl(int from, int to) {
        return new Interval(from, to);
    }

    static class Interval {
        final int begin;
        final int end;

        Interval(int begin, int end) {
            if (begin > end) throw new IllegalArgumentException();
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", begin, end);
        }
    }

    @Test
    public void test() {

    }

}
