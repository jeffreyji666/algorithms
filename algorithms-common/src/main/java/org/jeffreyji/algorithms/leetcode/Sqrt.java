package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月2日 下午4:34:14
 * @comment:
 */

public class Sqrt {
    public static void main(String[] args) {
        System.out.println(sqrt(1));
        System.out.println(sqrt(2));
        System.out.println(sqrt(3));
        System.out.println(sqrt(4));
        System.out.println(sqrt(5));
        System.out.println(sqrt(6));
        System.out.println(sqrt(7));
        System.out.println(sqrt(8));
        System.out.println(sqrt(9));
    }

    public static int sqrt(int x) {
        long lo = 0;
        long hi = x;

        while (hi >= lo) {
            long mid = (lo + hi) / 2;
            if (x < mid * mid) {
                hi = mid - 1; // not hi = mid
            } else {
                lo = mid + 1;
            }
        }
        return (int) hi;
    }
}
