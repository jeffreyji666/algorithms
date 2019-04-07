package org.jeffreyji.algorithms.toutiao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiwengang
 * @since 2019/3/26 下午10:41
 * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。
 * 示例：
 *
 *
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 *
 *
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 *
 *
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。
 */
public class FindUniqueMaxLengthSubstring {

    public static void main(String[] args) {
        System.out.println(findUniqueMaxLengthSubstring("pdwwdkew"));
    }

    public static int findUniqueMaxLengthSubstring(String s) {
        Map<Character, Integer> char2Index = new HashMap<Character, Integer>();
        int maxLength = 0;
        int now = 0;
        for (int i = 0; i < s.length(); i++) {
            if (char2Index.containsKey(s.charAt(i))) {
                now = Math.max(char2Index.get(s.charAt(i)) + 1, now);
                if ((i - now + 1) > maxLength) {
                    maxLength = i - now + 1;
                }
            } else {
                if ((i - now + 1) > maxLength) {
                    maxLength = i - now + 1;
                }
            }
            char2Index.put(s.charAt(i), i);

            System.out.println("i:" + i + ",now:" + now + ",maxLength:" + maxLength + ",char2Index:" + char2Index);
        }
        return maxLength;
    }
}
