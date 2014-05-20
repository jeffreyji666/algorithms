package org.jeffreyji.algorithms.leetcode;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月20日 下午8:17:19
 * @comment:Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * 
 * 分析：
 * 可以用一个table来存储每一个字符在字符串中的位置。其实这是一个对于字符串问题的通用方法，因为字符本质上就是一个Unique的数字，
 * 因此建立一个数组，数组的下标表示这个字符的ASCII码，元素表示其在字符串中的位置即可。
 * 另外一个逻辑就是当遇到和之前重复的字符（前一次出现称为Occur 1, 这一次出现称为Occur 2）时，
 * 即遇到了一个可能的最大值；进行比较后，将下一次扫描的起点设置为刚才这个重复字符的Occur 1的后一位即可。
 */

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();

        int[] countTable = new int[256];
        Arrays.fill(countTable, -1);
        int max = 1;
        int start = 0;
        int end = 1;

        countTable[s.charAt(0)] = 0;
        while (end < length) {
            // Has not reached a duplicate char
            if (countTable[s.charAt(end)] >= start) {
                start = countTable[s.charAt(end)] + 1;
            }
            max = Math.max(max, end - start + 1);
            countTable[s.charAt(end)] = end;
            end++;
        }
        return max;
    }
}
