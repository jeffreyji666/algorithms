package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月2日 上午1:08:12
 * @comment:Given an input string, reverse the string word by word. 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 */
public class ReversedString {
    public static void main(String[] args) {
        System.out.println(reverseWords(" 1"));
    }

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = strs.length - 1; i >= 0; i--) {
            if (!strs[i].equals("")) {
                sb.append(strs[i]).append(" ");
            }
        }

        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
