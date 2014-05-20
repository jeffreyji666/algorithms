package org.jeffreyji.algorithms.leetcode;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月8日 下午1:36:35
 * @comment:Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array A = [1,1,2],
 * Your function should return length = 2, and A is now [1,2].
 */

public class RemoveDuplicatesfromSortedArray {
    public static void main(String[] args) {
        int[] a = { 1, 1, 2 };
        System.out.println(removeDuplicates(a));
    }

    public static int removeDuplicates(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int len = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1]) {
                a[len++] = a[i];
            }
        }
        System.out.println(Arrays.toString(a));
        return len;
    }
}
