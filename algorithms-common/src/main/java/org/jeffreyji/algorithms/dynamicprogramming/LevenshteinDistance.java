package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * 字符串编辑距离
 * 
 * 这是一种字符串之间相似度计算的方法。 给定字符串S、T，将S转换T所需要的插入、删除、替代操作的数量叫做S到T的编辑路径。 其中最短的路径叫做编辑距离。
 * 
 * 这里使用了一种动态规划的思想求编辑距离。 levenshtein (edit distance)
 * 
 * @author: wgji
 * @date：2014年4月9日 下午8:31:44
 * @comment: *
 */
public class LevenshteinDistance {
    public static int ld(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int si;
        int ti;
        char ch1;
        char ch2;
        int cost;
        if (sLen == 0) {
            return tLen;
        }
        if (tLen == 0) {
            return sLen;
        }
        int d[][] = new int[sLen + 1][tLen + 1];
        for (si = 0; si <= sLen; si++) {
            d[si][0] = si;
        }
        for (ti = 0; ti <= tLen; ti++) {
            d[0][ti] = ti;
        }
        for (si = 1; si <= sLen; si++) {
            ch1 = s.charAt(si - 1);
            for (ti = 1; ti <= tLen; ti++) {
                ch2 = t.charAt(ti - 1);
                if (ch1 == ch2) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                d[si][ti] = Math.min(Math.min(d[si - 1][ti] + 1, d[si][ti - 1] + 1), d[si - 1][ti - 1] + cost);
            }
        }
        return d[sLen][tLen];
    }

    public static void main(String[] args) {
        String src = "hello world!";
        String tar = "hello fuckd";
        System.out.println(ld(src, tar));
    }
}
