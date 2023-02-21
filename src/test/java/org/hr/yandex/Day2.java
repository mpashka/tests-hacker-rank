package org.hr.yandex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day2 {

    /**
     * Дан массив из нулей и единиц. Нужно определить, какой максимальный по длине подынтервал единиц можно получить,
     * удалив ровно один элемент массива.
     * maxOnes(new int[]{0, 0}) == 0
     * maxOnes(new int[]{1, 0}) == 1
     * maxOnes(new int[]{0, 1}) == 1
     * maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1}) == 5
     * maxOnes(new int[]{1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}) == 6
     *
     * Удалять один элемент из массива обязательно.
     */

    public int maxOnes(int[] arr) {
        int prev = 0, next = 0, max = 0;

        if (arr.length == 0) {
            throw new IllegalArgumentException("Empty arr");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                next++;
            } else {
                max = Math.max(max, next + prev);
                prev = next;
                next = 0;
            }
        }
        max = Math.max(max, next + prev);
        if (max == arr.length) {
            max--;
        }
        return max;
    }


    /*
    Дан список ненулевой длины состоящий из направлений. Направление обозначается одним из четырех символов:
    L - Left
    R - Right
    U - Up
    D - Down
    Каждый элемент перемещает нас на 1 в заданном направлении.
    известно, что петли (возврат в уже посещенную точку) дают нулевое перемещение и являются пустой тратой времени. Нужно удалить из списка все петли и вернуть оптимизированный короткий маршрут, например:
    [R,D,L,U,R] -> [R]
    [R,D,L,R,U,U,R] -> [R,U,R]
    Важно отметить, что цель не просто попасть в ту же самую конечную точку, но и придерживаться первоначального маршрута (не срезать по прямой):
    [D,R,U] -> [D,R,U]
    Вернуть нужно массив направлений (а не массив посещенных точек).

    0 -> v(0,0null-0),                                path(0,0) - 1,0R
    1 -> v(0,0null-0;1,0R-1),                         path(0,0;1,0)
    2 -> v(0,0null-0;1,0R-1;1,-1D-2),                 path(0,0;1,0;1,-1)
    3 -> v(0,0null-0;1,0R-1;1,-1D-2;0,-1L-3),         path(0,0;1,0;1,-1;0,-1) 0,0U
    4 -> v(0,0null-0),                                path(0,0) 1,0R
    5 -> v(0,0null-0),                                path(0,0;1,0R)
    */
    public char[] removeLoops(char[] direction) {
        Point pos = new Point(0,0, ' ');
        Map<Point, Integer> visited = new HashMap<>(direction.length);
        List<Point> path = new ArrayList<>(direction.length);
        for (int i = 0; i <= direction.length; i++) {
            Integer prev = visited.get(pos);
            if (prev != null) {
                for (int j = i-1; j > prev; j--) {
                    visited.remove(path.get(j));
                    path.remove(j);
                }
            } else {
                visited.put(pos, path.size());
                path.add(pos);
            }
            pos = i < direction.length ? pos.move(direction[i]) : null;
        }
        char[] result = new char[path.size()-1];
        for (int i = 1; i < path.size(); i++) {
            result[i-1] = path.get(i).direction;
        }
        return result;
    }

    static class Point {
        int x, y;
        char direction;
        Point(int x, int y, char direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public Point move(char direction) {
            int nx = x, ny = y;
            switch (direction) {
                case 'R': nx++; break;
                case 'L': nx--; break;
                case 'U': ny++; break;
                case 'D': ny--; break;
            }
            return new Point(nx, ny, direction);
        }

        public boolean equals(Object other) {
            Point o = (Point) other;
            return x == o.x && y == o.y;
        }

        public int hashCode() {
            return x * 31 + y;
        }
    }
}

