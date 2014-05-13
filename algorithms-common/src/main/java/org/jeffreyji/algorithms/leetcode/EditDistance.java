package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月13日 下午11:18:07
 * @comment:Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 *  (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("hello world!", "hello fuckd"));
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length1;
        }

        int[][] ed = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            ed[i][0] = i;
        }
        for (int i = 0; i <= length2; i++) {
            ed[0][i] = i;
        }
        for (int i = 1; i <= length1; i++) {
            char ch1 = word1.charAt(i - 1);
            for (int j = 1; j <= length2; j++) {
                char ch2 = word2.charAt(j - 1);
                ed[i][j] = Math.min(Math.min(ed[i - 1][j] + 1, ed[i][j - 1] + 1), ed[i - 1][j - 1]
                        + (ch1 == ch2 ? 0 : 1));
            }
        }
        return ed[length1][length2];
    }
}
