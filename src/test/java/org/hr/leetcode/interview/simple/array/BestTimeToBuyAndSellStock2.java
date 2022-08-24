package org.hr.leetcode.interview.simple.array;

import org.hr.utils.IntArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BestTimeToBuyAndSellStock2 {
    public int maxProfit(int[] prices) {
        boolean buy = true;
        int lastPrice = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (buy) {
                if (prices[i] < lastPrice) {
                    lastPrice = prices[i];
                } else {
                    buy = false;
                    profit += (prices[i] - lastPrice);
                    lastPrice = prices[i];
                }
            } else {
                if (prices[i] > lastPrice) {
                    profit += (prices[i] - lastPrice);
                    lastPrice = prices[i];
                } else {
                    buy = true;
                    lastPrice = prices[i];
                }
            }
        }
        return profit;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "7,1,5,3,6,4:7",
            "7,6,5,4,3,2,1,2,3,4,5,3,4,5,6,5,4:7",
            "1,2,3,4,5:4",
            "7,6,4,3,1:0",
            "7:0",
            "1,7:6",
            "1,7,1:6",
            "1,7,1,8:13",
            "1,7,1,6:11",
            "7,4:0"
    }, delimiter = ':')
    public void test1(@ConvertWith(IntArrayConverter.class) int[] prices, int profit) {
        assertThat(maxProfit(prices), is(profit));
    }
}
