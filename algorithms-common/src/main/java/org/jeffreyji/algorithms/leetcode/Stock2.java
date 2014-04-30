package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年4月30日 上午10:49:28
 * @comment: Say you have an array for which the ith element is the price of a given stock on day i. 
 * Design an algorithm to find the maximum profit. You may complete as many transactions 
 * as you like (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */
public class Stock2 {
    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 4, 5, 6 };
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i])
                total += prices[i + 1] - prices[i];
        }

        return total;
    }
}
