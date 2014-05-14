package org.jeffreyji.algorithms.dynamicprogramming;

import java.util.Arrays;

/** 
 * @author:  wgji
 * @date：2014年5月14日 下午1:32:11 
 * @comment: 
 */
/**
 * 对称字符串的最大长度（字符串）<br/>
 * 题目：输入一个字符串，输出该字符串中对称的子字符串的最大长度。<br/>
 * 比如输入字符串“google”，由于该字符串里最长的对称子字符串是“goog”，因此输出4。
 * 
 * @author Joeson
 * 
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "acbcaacbcd";
        char[] str = init(s);
        System.out.println(Arrays.toString(str));
        manacher(str);
        System.out.println(Integer.toString(10));
    }

    /**
     * 返回例如 #a#c#b#c#a#a#c#b#c#d#形式的字符串数组
     * 
     * @param s
     * @return
     */
    public static char[] init(String s) {
        char[] str = new char[s.length() * 2 + 1];

        int i = 0;
        for (; i < s.length(); i++) {
            str[2 * i] = '#';
            str[2 * i + 1] = s.charAt(i);
        }
        str[2 * i] = '#';

        return str;
    }

    /**
     * 
     * 
     * @param str
     */
    public static void manacher(char[] s) {
        int rad[] = new int[s.length];

        int i = 1, j = 0, k;

        // 记录最长的回文串的长度
        int maxLen = 0;
        while (i < s.length) {
            // 扫描得出rad值
            while (i - j - 1 > -1 && i + j + 1 < s.length && s[i - j - 1] == s[i + j + 1])
                j++;
            rad[i] = j;

            maxLen = maxLen > j ? maxLen : j;

            k = 1;
            while (k <= rad[i] && rad[i - k] != rad[i] - k) {
                rad[i + k] = Math.min(rad[i - k], rad[i] - k);
                k++;
            }
            i = i + k;
            j = Math.max(j - k, 0);
        }

        System.out.println(Arrays.toString(rad));
        System.out.println("最长回文串长度： " + maxLen);
    }

}
