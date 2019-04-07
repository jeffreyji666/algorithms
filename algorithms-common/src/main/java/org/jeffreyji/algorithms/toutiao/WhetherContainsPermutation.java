package org.jeffreyji.algorithms.toutiao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jiwengang
 * @since 2019/3/27 上午9:10
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class WhetherContainsPermutation {

    public static void main(String[] args) {
        System.out.println(checkInclusion("abc", "bcadd"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (null == s1 || s1.length() == 0 || null == s2 || s2.length() == 0) {
            return false;
        }
        Set<String> res = new HashSet<String>();
        permutation(s1.toCharArray(), 0, res);
        System.out.println(res);
        for (String subS1 : res) {
            if (s2.contains(subS1)) {
                return true;
            }
        }
        return false;
    }

    public static void permutation(char[] chars, int begin, Set<String> res) {
        // 如果是最后一个元素了，就输出排列结果
        if (chars.length - 1 == begin) {
            res.add(new String(chars));
        } else {
            // 对当前还未处理的字符串进行处理，每个字符都可以作为当前处理位置的元素
            for (int i = begin; i < chars.length; i++) {
                swap(chars, i, begin);
                // 处理下一个位置
                permutation(chars, begin + 1, res);
                swap(chars, i, begin);
            }
        }
    }

    public static void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}
