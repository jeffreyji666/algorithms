package org.jeffreyji.algorithms.leetcode;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月1日 下午5:16:58
 * @comment:Given a sorted array of integers, find the starting and ending position of a given target value.
 *  Your algorithm's runtime complexity must be in the order of O(log n). 
 *  If the target is not found in the array, return [-1, -1]. 
 *  For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 */
public class SearchForRange {
    public static void main(String[] args) {
        int[] a = { 5, 6, 6, 7, 7, 7, 8, 8, 8, 10 };
        int[] res = searchRange(a, 8);
        System.out.println(Arrays.toString(res));
    }

    public static int[] searchRange(int[] a, int target) {
        int[] bound = { -1, -1 };

        // search for left bound
        int start = 0;
        int end = a.length - 1;
        while (start + 1 < end) {
            int mid = (end + start) / 2;
            if (a[mid] == target) {
                end = mid;
            } else if (a[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (a[start] == target) {
            bound[0] = start;
        } else if (a[end] == target) {
            bound[0] = end;
        } else {
            return bound;
        }

        // search for right bound
        start = 0;
        end = a.length - 1;
        while (start + 1 < end) {
            int mid = (end + start) / 2;
            if (a[mid] == target) {
                start = mid;
            } else if (a[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (a[end] == target) {
            bound[1] = end;
        } else if (a[start] == target) {
            bound[1] = start;
        } else {
            return bound;
        }

        return bound;
    }
}
