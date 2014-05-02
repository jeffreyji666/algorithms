package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月2日 下午9:49:08
 * @comment:
 */

public class PlusOne {
    public static void main(String[] args) {
        int[] digits = { 8, 9, 9 };
        int[] res = plusOne(digits);
        for (int item : res) {
            System.out.print(item + " ");
        }
    }

    public static int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int num = digits[digits.length - 1] + 1;
        if (num < 10) {
            digits[digits.length - 1] = num;
            return digits;
        } else {
            int[] res = new int[digits.length + 1];
            res[res.length - 1] = num % 10;
            num = num / 10;
            for (int i = digits.length - 2; i >= 0; i--) {
                res[i + 1] = (digits[i] + num) % 10;
                num = (digits[i] + num) / 10;
            }
            if (num > 0) {
                res[0] = num;
            } else {
                for (int i = 1; i < res.length; i++) {
                    res[i - 1] = res[i];
                }
            }
            return res;
        }
    }

    public int[] plusOne2(int[] digits) {
        int carries = 1;
        for (int i = digits.length - 1; i >= 0 && carries > 0; i--) { // fast break when carries equals zero
            int sum = digits[i] + carries;
            digits[i] = sum % 10;
            carries = sum / 10;
        }
        if (carries == 0) {
            return digits;
        }
        int[] rst = new int[digits.length + 1];
        rst[0] = 1;
        for (int i = 1; i < rst.length; i++) {
            rst[i] = digits[i - 1];
        }
        return rst;
    }
}
