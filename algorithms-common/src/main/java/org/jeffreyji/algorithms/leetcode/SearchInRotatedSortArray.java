package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月21日 下午8:49:20 
 * @comment: Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */

public class SearchInRotatedSortArray {

    public static void main(String[] args) {
        int[] a = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(search(a, 1));
        System.out.println(search(a, 5));
        int[] b = { 6, 7, 0, 1, 2, 4, 5 };
        System.out.println(search(b, 1));
        System.out.println(search(b, 6));
        System.out.println(search(b, 3));
    }

    public static int search(int[] a, int target) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (a[middle] == target) {
                return middle;
            }
            if (a[middle] < a[right]) {
                if (target > a[middle] && target <= a[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                if (target >= a[left] && target < a[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        return -1;
    }
}
 