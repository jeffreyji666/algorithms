package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年4月30日 上午9:53:36
 * @comment:Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 */
public class Stock1 {
    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 4, 5, 6 };
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int profit = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
}
