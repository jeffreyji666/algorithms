package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月14日 下午1:40:56
 * @comment:Determine whether an integer is a palindrome. Do this without extra space.
 */

public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(1232));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x > 0 && x < 10) {
            return true;
        }
        return x == reverse(x);
    }

    public static int reverse(int x) {
        int rst = 0;
        while (x != 0) {
            rst = rst * 10 + x % 10;
            x = x / 10;
        }
        return rst;
    }
}
