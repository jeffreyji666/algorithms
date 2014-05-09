package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月9日 下午3:22:47
 * @comment: Given an array of integers, every element appears twice except for one. Find that single one. 
 * Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class SingleNumber {
    public static void main(String[] args) {
        int[] a = { 1, 1, 2, 3, 4, 2, 3 };
        System.out.println(singleNumber(a));
    }

    public static int singleNumber(int[] a) {
        int res = a[0];
        for (int i = 1; i < a.length; i++) {
            res = res ^ a[i];
        }
        return res;
    }
}
