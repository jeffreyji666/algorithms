package org.jeffreyji.algorithms.leetcode;

import java.math.BigInteger;

/**
 * @author: wgji
 * @date：2014年5月8日 上午10:40:16
 * @comment:Given two binary strings, return their sum (also a binary string). 
 * For example, a = "11" b = "1" Return "100".
 */
public class AddBinary {
    public static void main(String[] args) {
        String d = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String c = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        System.out.println(addBinary(c, d));
        System.out.println(addBinary2(c, d));

    }

    public static String addBinary(String a, String b) {
        if (a == null || b == null) {
            return null;
        }
        BigInteger ai = new BigInteger(a, 2);
        BigInteger bi = new BigInteger(b, 2);
        BigInteger sum = ai.add(bi);

        return sum.toString(2);

    }

    public static String addBinary2(String a, String b) {
        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }

        int pa = a.length() - 1;
        int pb = b.length() - 1;
        int carries = 0;
        String rst = "";

        while (pb >= 0) {
            int sum = (int) (a.charAt(pa) - '0') + (int) (b.charAt(pb) - '0') + carries;
            rst = String.valueOf(sum % 2) + rst;
            carries = sum / 2;
            pa--;
            pb--;
        }

        while (pa >= 0) {
            int sum = (int) (a.charAt(pa) - '0') + carries;
            rst = String.valueOf(sum % 2) + rst;
            carries = sum / 2;
            pa--;
        }

        if (carries == 1) {
            rst = "1" + rst;
        }
        return rst;
    }
}
