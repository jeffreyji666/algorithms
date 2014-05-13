package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月13日 上午10:06:56
 * @comment:Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class SingleNumber2 {

    public static void main(String[] args) {
        int[] a = { 1, 1, 1, 2, 3, 2, 2 };
        System.out.println(singleNumber(a));
    }

    public static int singleNumber(int a[]) {
        int ones = 0, twos = 0, xthrees = 0;
        for (int i = 0; i < a.length; ++i) {
            twos |= (ones & a[i]);
            ones ^= a[i];
            xthrees = ~(ones & twos);
            ones &= xthrees;
            twos &= xthrees;
        }

        return ones;
    }
}
