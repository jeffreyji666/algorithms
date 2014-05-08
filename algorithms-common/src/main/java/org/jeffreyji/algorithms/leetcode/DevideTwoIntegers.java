package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月8日 下午7:21:41
 * @comment:Divide two integers without using multiplication, division and mod operator.
 */

public class DevideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(divide(10, 2));
        System.out.println(divide2(10, 2));
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) {
            return 0;
        }

        boolean tag = ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) ? true : false;

        int result = 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (dividend >= divisor) {
            dividend -= divisor;
            result++;
        }

        return tag ? -result : result;
    }

    public static int divide2(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) {
            return 0;
        }
        boolean negative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int ans = 0;

        while (a >= b) {
            int shift = 0;
            while ((b << shift) <= a) {
                shift++;
            }
            ans += 1 << (shift - 1);
            a = a - (b << (shift - 1));
        }

        return negative ? -ans : ans;
    }
}
