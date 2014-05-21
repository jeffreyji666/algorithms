package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月21日 下午9:17:02
 * @comment:Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 */

public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] a = { 1, 3, 5, 6 };
        System.out.println(searchInsert(a, 5));
        System.out.println(searchInsert(a, 2));
        System.out.println(searchInsert(a, 7));
        System.out.println(searchInsert(a, 0));
    }

    public static int searchInsert(int[] a, int target) {
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] == target) {
                return m;
            }
            if (a[m] > target) {
                r = m - 1;
            }
            if (a[m] < target) {
                l = m + 1;
            }
        }
        return l;
    }
}
