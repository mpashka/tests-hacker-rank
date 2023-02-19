package org.hr.leetcode.medium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Test29_DivideTwoIntegers {
    public int divide(int dividendI, int divisorI) {
        if (dividendI == Integer.MIN_VALUE && divisorI == -1) {
            return Integer.MAX_VALUE;
        }
        long dividend = dividendI, divisor = divisorI;
        boolean minus = false;
        if (dividend < 0 && divisor < 0) {
                dividend = -dividend;
                divisor = -divisor;
        } else if (dividend < 0) {
            minus = true;
            dividend = -dividend;
        } else if (divisor < 0) {
            minus = true;
            divisor = -divisor;
        }
        int cnt = 0;
        while (dividend >= divisor) {
            cnt++;
            divisor <<= 1;
        }
        divisor >>= 1;
        int result = 0;
        for (int i = 0; i < cnt; i++) {
            result <<= 1;
            if (dividend >= divisor) {
                result++;
                dividend -= divisor;
            }
            divisor >>= 1;
        }
        return minus ? -result : result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,0",
            "10,1,10",
            "10,2,5",
            "10,3,3",
            "10,4,2",
            "10,5,2",
            "10,6,1",
            "10,7,1",
            "10,8,1",
            "10,9,1",
            "10,10,1",
            "3,2,1",
            "7,-3,-2",
            "-3,2,-1",
            "-2147483648,1,-2147483648",
            "-2147483648,-1,2147483647",
            "2147483647,1,2147483647",
            "2147483647,-1,-2147483647",
    })
    public void test(int dividend, int divisor, int result) {
        assertThat(divide(dividend, divisor), is(result));
    }
}
