package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月23日 下午9:20:44
 * @comment:Implement pow(x, n).
 */

public class Pow {
    public static void main(String[] args) {
        System.out.println(pow(2.0, 3));
    }

    public static double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        boolean isNegative = false;
        if (n < 0) {
            isNegative = true;
            n *= -1;
        }

        int k = n / 2;
        int l = n - k * 2;
        double t1 = pow(x, k);
        double t2 = pow(x, l);
        if (isNegative) {
            return 1 / (t1 * t1 * t2);
        } else {
            return t1 * t1 * t2;
        }
    }
}
