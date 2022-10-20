package org.hr.leetcode.interview.hard.others;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int volume = 0, idx, anotherIdx, direction, startLen, anotherLen;
        if (height[0] < height[height.length - 1]) {
            idx = 0;
            anotherIdx = height.length - 1;
            direction = 1;
        } else {
            anotherIdx = 0;
            idx = height.length - 1;
            direction = -1;
        }
        startLen = height[idx];
        anotherLen = height[anotherIdx];

        while (true) {
            idx += direction;
            if (idx == anotherIdx) {
                return volume;
            }
            int len = height[idx];
            if (len < startLen) {
                volume += startLen - len;
            } else {
                if (len > anotherLen) {
                    // switch direction
                    int o = anotherIdx;
                    anotherIdx = idx;
                    idx = o;
                    direction = -direction;
                    anotherLen = len;
                }
                startLen = height[idx];
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0:0",
            "0,2:0",
            "0,1,0,2,1,0,1,3,2,1,2,1:6",
            "4,2,0,3,2,5:9",
    }, delimiter = ':')
    public void test(@ConvertWith(IntArrayConverter.class) int[] height, int result) {
        assertThat(trap(height), is(result));
    }
}
