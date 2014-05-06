package org.jeffreyji.algorithms.leetcode;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月6日 上午11:22:48
 * @comment:There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */

public class Candy {
    public static void main(String[] args) {
        int[] ratings = { 1, 3, 6, 3, 9, 3 };
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] count = new int[ratings.length];
        Arrays.fill(count, 1);
        int sum = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                count[i] = count[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 1; i >= 1; i--) {
            sum += count[i];
            if (ratings[i - 1] > ratings[i] && count[i - 1] <= count[i]) { // second round has two conditions
                count[i - 1] = count[i] + 1;
            }
        }
        sum += count[0];
        return sum;
    }
}
