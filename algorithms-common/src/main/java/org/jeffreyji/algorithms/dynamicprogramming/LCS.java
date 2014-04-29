package org.jeffreyji.algorithms.dynamicprogramming;

/** 
 * @author:  wgji
 * @date：2014年4月29日 下午5:48:52 
 * @comment: 最长公共字符串
 */
import java.util.Arrays;

// 最长公共子串 Longest Common Subsequence
public class LCS {
    static int dp[][] = null;

    public static void main(String[] args) {
        String a = "ABCABCBAASJKDFHSDDSJSAHJSD";
        String b = "CBABCABCCSDESJASDFHSDFSW";

        dp = new int[a.length() + 1][b.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(lcs2(a.toCharArray(), a.length(), b.toCharArray(), b.length()));
        System.out.println(lcs3(a.toCharArray(), a.length(), b.toCharArray(), b.length()));
        // print();
        System.out.println(lcs(a.toCharArray(), a.length(), b.toCharArray(), b.length()));

    }

    // 纯递归O(m*2^n)
    public static int lcs(char[] A, int m, char[] B, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (A[m - 1] == B[n - 1]) {
            return 1 + lcs(A, m - 1, B, n - 1);
        } else {
            return Math.max(lcs(A, m, B, n - 1), lcs(A, m - 1, B, n));
        }
    }

    // DP, top-down O(n^2)
    public static int lcs2(char[] A, int m, char[] B, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        // 如果已经存在dp数组中，直接返回
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int res = 0;
        if (A[m - 1] == B[n - 1]) {
            res = 1 + lcs2(A, m - 1, B, n - 1);
        } else {
            res = Math.max(lcs2(A, m, B, n - 1), lcs2(A, m - 1, B, n));
        }
        dp[m][n] = res; // 把新值记录到dp数组中
        return res;
    }

    // DP, bottom-up O(n^2)
    public static int lcs3(char[] A, int m, char[] B, int n) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void print() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
