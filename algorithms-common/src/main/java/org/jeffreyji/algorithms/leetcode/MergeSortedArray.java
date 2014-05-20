package org.jeffreyji.algorithms.leetcode;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月12日 下午6:20:45
 * @comment:Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 */

public class MergeSortedArray {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 0, 0, 0 };
        int[] b = { 2, 5, 6 };

        merge(a, 3, b, 3);
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int a[], int m, int b[], int n) {
        if (a == null || b == null){
            return;
        }
        int idx1 = m - 1;
        int idx2 = n - 1;
        int len = m + n - 1;
        while (idx1 >= 0 && idx2 >= 0) {
            if (a[idx1] > b[idx2]) {
                a[len--] = a[idx1--];
            } else {
                a[len--] = b[idx2--];
            }
        }
        while (idx2 >= 0) {
            a[len--] = b[idx2--];
        }
    }
}
