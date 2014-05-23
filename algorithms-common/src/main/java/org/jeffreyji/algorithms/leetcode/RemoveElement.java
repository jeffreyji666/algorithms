package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月24日 上午12:16:28
 * @comment:Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

public class RemoveElement {

    public static void main(String[] args) {
        int[] a = { 1, 1, 2, 10, 3, 4, 5, 4 };
        int len = removeElement(a, 4);
        for (int i = 0; i < len; i++) {
            System.out.printf("%d,", a[i]);
        }
    }

    public static int removeElement(int[] a, int elem) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int len = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != elem) {
                a[len++] = a[i];
            }
        }
        return len;
    }

}
