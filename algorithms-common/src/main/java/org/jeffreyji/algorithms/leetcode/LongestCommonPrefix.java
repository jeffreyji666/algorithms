package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月9日 上午11:55:19
 * @comment:Write a function to find the longest common prefix string amongst an array of strings.
 */

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = { "hello", "hello workd", "helloWorld" };
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = "";
        if (strs.length == 0) {
            return prefix;
        }

        int k = 0;
        while (true) {
            char p = ' ';
            int i;
            for (i = 0; i < strs.length; i++) {
                if (k == strs[i].length()) {
                    break;
                }
                if (i == 0) {
                    p = strs[i].charAt(k);
                }

                if (p != strs[i].charAt(k)) {
                    break;
                }
            }
            if (i != strs.length) {
                break;
            }
            prefix += p;
            k++;
        }

        return prefix;
    }
}
