package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月14日 下午4:30:37
 * @comment:Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example, Given s = "Hello World",return 5.
 */

public class LengthOfLastWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("test test1"));
        System.out.println(lengthOfLastWord2("test test1"));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String[] strs = s.split("\\s{1,}");
        if (strs.length < 1) {
            return 0;
        }
        return strs[strs.length - 1].length();
    }
    
    public static int lengthOfLastWord2(String s) {
        int length = 0;
        char[] chars = s.toCharArray();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (length == 0) {
                if (chars[i] == ' ') {
                    continue;
                } else {
                    length++;
                }
            } else {
                if (chars[i] == ' ') {
                    break;
                } else {
                    length++;
                }
            }
        }

        return length;
    }
}
