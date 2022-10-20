package org.hr.leetcode.interview.hard.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        SortedMap<Integer, List<Edge>> edges = new TreeMap<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] buildingNum = buildings[i];
            int left = buildingNum[0], right = buildingNum[1], height = buildingNum[2];
            Building building = new Building(i, height);
            Edge begin = new Edge(i, true, left, height, building);
            Edge end = new Edge(i, false, right, height, building);
            edges.computeIfAbsent(left, l -> new ArrayList<>()).add(begin);
            edges.computeIfAbsent(right, l -> new ArrayList<>()).add(end);
        }

        BackBuildings backBuildings = new BackBuildings();
        List<List<Integer>> result = new ArrayList<>();
        int height = 0;
        for (Map.Entry<Integer, List<Edge>> posEntry : edges.entrySet()) {
            for (Edge edge : posEntry.getValue()) {
                if (edge.begin) {
                    backBuildings.addBuilding(edge.building);
                } else {
                    backBuildings.removeBuilding(edge.building);
                }
            }
            int newHeight = backBuildings.getHeight();
            if (newHeight != height) {
                result.add(List.of(posEntry.getKey(), newHeight));
                height = newHeight;
            }
        }
        return result;
    }

    private static class Edge {
        private int num;
        private boolean begin;
        private int pos;
        private int height;
        private Building building;

        public Edge(int num, boolean begin, int pos, int height, Building building) {
            this.num = num;
            this.begin = begin;
            this.pos = pos;
            this.height = height;
            this.building = building;
        }
    }

    private static class BackBuildings {
        // height; num
        private SortedMap<Integer, HashMap<Integer, Building>> backBuildings = new TreeMap<>();

        public void addBuilding(Building building) {
            backBuildings.computeIfAbsent(building.height, h -> new HashMap<>()).put(building.num, building);
        }

        public void removeBuilding(Building building) {
            HashMap<Integer, Building> h = backBuildings.get(building.height);
            h.remove(building.num);
            if (h.isEmpty()) {
                backBuildings.remove(building.height);
            }
        }

        public int getHeight() {
            return backBuildings.isEmpty() ? 0 : backBuildings.lastKey();
        }
    }

    private static class Building {
        private int num;
        private int height;

        public Building(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    @Test
    public void test1() {
        Function<List<List<Integer>>, int[][]> toArr = i -> i
                .stream()
                .map(ii -> ii.stream().mapToInt(iv -> iv).toArray())
                .toArray(int[][]::new);

        {
            int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
            int[][] result = {{2, 10}, {3, 15}, {7, 12}, {12, 0}, {15, 10}, {20, 8}, {24, 0}};
            assertThat(toArr.apply(getSkyline(buildings)), is(result));
        }

        {
            int[][] buildings = {{0,2,3},{2,5,3}};
            int[][] result = {{0,3},{5,0}};
            assertThat(toArr.apply(getSkyline(buildings)), is(result));
        }

        {
            int[][] buildings = {{0,2,3},{1,5,3}};
            int[][] result = {{0,3},{5,0}};
            assertThat(toArr.apply(getSkyline(buildings)), is(result));
        }
    }
}
