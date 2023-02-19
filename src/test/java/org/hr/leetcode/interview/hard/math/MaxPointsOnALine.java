package org.hr.leetcode.interview.hard.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MaxPointsOnALine {
    private static final long[] X0 = new long[]{0,1};
    private static final long[] Y0 = new long[]{1,0};

    public int maxPoints(int[][] points) {
        Map<K, int[]>[] pointsFrom = new Map[points.length - 1];
        for (int i = 0; i < points.length - 1; i++) {
            pointsFrom[i] = new HashMap<>(Map.of(new K(points[i], points[i+1]), new int[]{2}));
            for (int j = i+2; j < points.length; j++) {
                K k = new K(points[i], points[j]);
                pointsFrom[i].computeIfAbsent(k, k0 -> new int[]{1})[0]++;
            }
        }
        return Arrays.stream(pointsFrom).flatMap(m -> m.values().stream()).mapToInt(i -> i[0]).max().orElse(1);
    }

    private class K {
        private long x, y;

        public K(int[] point1, int[] point2) {
            long[] k = k(point1[0] - point2[0], point1[1] - point2[1]);
            this.x = k[0];
            this.y = k[1];
        }

        @Override
        public boolean equals(Object o) {
            K k = (K) o;
            return x == k.x && y == k.y;
        }

        @Override
        public int hashCode() {
            return (int) (31 * x + y);
        }
    }

    private long[] k(int x0, int y0) {
        long x = x0, y = y0;
        boolean neg = false;
        if (x == 0) {
            return X0;
        } else if (y == 0) {
            return Y0;
        } else if (x < 0 && y < 0) {
            x = -x;
            y = -y;
        } else if (x < 0 || y < 0) {
            x = Math.abs(x);
            y = Math.abs(y);
            neg = true;
        }

        long a = Math.max(x, y), b = Math.min(x, y), z;
        do {
            z = a % b;
            if (z == 0) {
                z = b;
                break;
            }
            a = b;
            b = z;
        } while (z > 1);
        return new long[]{x / z, y / z * (neg ? -1  : 1)};
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,1,2,2,3,3:3",
            "1,1,2,2,3,3,2,3:3",
            "1,1,3,2,5,3:3",
            "4,1,3,2,2,3,1,4:4",
            "4,1,3,2,2,3,1,4,1,1,5,3:4",
            "1,1,3,2,5,3,4,1,2,3,1,4:4",
            "1,1,3,2,5,3,4,1,2,3,1,4,2,1:4",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] points0, int points) {
        int[][] pointsArr = new int[points0.length / 2][2];
        for (int i = 0; i < points0.length; i += 2) {
            pointsArr[i / 2][0] = points0[i];
            pointsArr[i / 2][1] = points0[i + 1];
        }
        assertThat(maxPoints(pointsArr), is(points));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0,2,0,1",
            "1,2,1,2",
            "4,2,2,1",
            "4,-2,2,-1",
            "-4,2,2,-1",
            "5,3,5,3",
            "3,5,3,5",
            "6,10,3,5",
    })
    public void testK(int x0, int y0, int x, int y) {
        assertThat(k(x0, y0), is(new long[]{x, y}));
    }
}
