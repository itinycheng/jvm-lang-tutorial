package com.tiny.lang.java.interview;

/**
 * @author tiny.wang
 */
public class StockBuyStrategy {

    public static void main(String[] args) {
        int[] prices = new int[]{2, 9, 3, 2, 1, 3, 3, 2, 7, 5};
        System.out.println(findMaxProfit(prices));
        System.out.println(findMaxProfitInMultiTransMode(prices));

        prices = new int[]{4, 2, 6, 5, 9, 1, 10};
        System.out.println(findMaxProfit(prices));
        System.out.println(findMaxProfitInMultiTransMode(prices));

    }

    private static int findMaxProfitInMultiTransMode(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        /// find first smaller
        int begin = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] >= prices[i + 1]) {
                begin = i + 1;
            } else {
                break;
            }
        }

        // find next elem before smaller
        int indexBegin = begin;
        int totalProfit = 0;
        while (indexBegin < prices.length) {
            int indexEnd = findElemIndexBeforeSmall(indexBegin, prices);
            totalProfit += prices[indexEnd] - prices[indexBegin];
            indexBegin = indexEnd + 1;
        }
        return totalProfit;
    }

    private static int findElemIndexBeforeSmall(int begin, int[] prices) {
        int index = begin;
        while (index < prices.length - 1) {
            if (prices[index] < prices[index + 1]) {
                index++;
            } else {
                break;
            }
        }
        return index;
    }

    private static int findMaxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        // find first small index
        int max = 0;
        int begin = 0;
        while (begin < prices.length - 1) {
            begin = findNextSmallValueIdx(prices, begin);
            int nextMaxValue = findNextMaxValue(prices, begin);
            max = Math.max(max, nextMaxValue - prices[begin]);
            begin = begin + 1;
        }

        return max;
    }

    private static int findNextSmallValueIdx(int[] prices, int beginIdx) {
        int idx = beginIdx;
        for (int i = beginIdx; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                return i;
            }

            idx = i + 1;
        }

        return idx;
    }

    private static int findNextMaxValue(int[] prices, int begin) {
        if (begin >= prices.length - 1) {
            return 0;
        }

        int max = 0;
        for (int i = begin; i < prices.length; i++) {
            if (max < prices[i]) {
                max = prices[i];
            }
        }
        return max;
    }

}
