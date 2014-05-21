package org.jeffreyji.algorithms.leetcode;


/**
 * @author: wgji
 * @date：2014年5月20日 下午2:57:11
 * @comment:Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */

public class LongestPalindrome {
    public static void main(String[] args) {
        //System.out.println(longestPalindrome3("aadbcdddcbd"));
        System.out.println(getPalindromeLength("aadbcddcbd"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int length = s.length();
        int max = 0;
        String result = "";
        for (int i = 1; i <= 2 * length - 1; i++) {
            int count = 1;
            while (i - count >= 0 && i + count <= 2 * length && get(s, i - count) == get(s, i + count)) {
                count++;
            }
            count--; // there will be one extra count for the outbound #
            if (count > max) {
                result = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }

        return result;
    }

    private static char get(String s, int i) {
        if (i % 2 == 0)
            return '#';
        else
            return s.charAt(i / 2);
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i >= j) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }
            }
        }

        int maxLen = 1;
        int rf = 0, rt = 0;
        for (int k = 1; k < s.length(); k++) {
            for (int i = 0; i < s.length() - k; i++) {
                int j = i + k;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j]) {
                        if (k + 1 > maxLen) {
                            maxLen = k + 1;
                            rf = i;
                            rt = j;
                        }
                    }
                }
            }
        }
        return s.substring(rf, rt + 1);
    }

    private static int[] next;

    private static void getNext(String s) {
        int i = 0;
        int j = -1;

        next[0] = -1;
        while (i < s.length()) {
            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }

    // 用KMP算法做求出最长的前缀匹配
    private static int compare(String pattern, String s) {
        int i, j;

        i = 0;
        j = 0;
        int maxLen = 0;
        while (i < s.length()) {
            if (j == -1 || pattern.charAt(j) == s.charAt(i)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j > maxLen) {
                maxLen = j;
            }
            if (j == pattern.length()) {
                return maxLen;
            }
        }
        return maxLen;
    }

    public static String longestPalindrome3(String s) {
        String reverString = new StringBuilder(s).reverse().toString(); // 求得到 输入string 的reverse
        next = new int[s.length() + 1];

        String maxPal = "";
        int maxLen = 0;
        int len;
        // 枚举所有后缀
        for (int i = 0; i < s.length(); i++) {
            String suffix = reverString.substring(i);
            if (suffix.length() < maxLen) {
                break;
            }
            getNext(suffix);
            len = compare(suffix, s);
            if (len > maxLen) {
                maxPal = suffix.substring(0, len);
                maxLen = len;
            }

        }
        return maxPal;
    }

    public static int getPalindromeLength(String str) {
        // 1.构造新的字符串
        // 为了避免奇数回文和偶数回文的不同处理问题，在原字符串中插入'#'，将所有回文变成奇数回文
        StringBuilder newStr = new StringBuilder();
        newStr.append('#');
        for (int i = 0; i < str.length(); i++) {
            newStr.append(str.charAt(i));
            newStr.append('#');
        }

        // rad[i]表示以i为中心的回文的最大半径，i至少为1，即该字符本身
        int[] rad = new int[newStr.length()];
        // right表示已知的回文中，最右的边界的坐标
        int right = -1;
        // id表示已知的回文中，拥有最右边界的回文的中点坐标
        int id = -1;
        // 2.计算所有的rad
        // 这个算法是O(n)的，因为right只会随着里层while的迭代而增长，不会减少。
        for (int i = 0; i < newStr.length(); i++) {
            // 2.1.确定一个最小的半径
            int r = 1;
            if (i <= right) {
                r = Math.min(rad[id] - i + id, rad[2 * id - i]);
            }
            // 2.2.尝试更大的半径
            while (i - r >= 0 && i + r < newStr.length() && newStr.charAt(i - r) == newStr.charAt(i + r)) {
                r++;
            }
            // 2.3.更新边界和回文中心坐标
            if (i + r - 1 > right) {
                right = i + r - 1;
                id = i;
            }
            rad[i] = r;
        }

        // 3.扫描一遍rad数组，找出最大的半径
        int maxLength = 0;
        int centerIndex = 0;
        for (int i = 0; i < rad.length; i++) {
            if (rad[i] > maxLength) {
                maxLength = rad[i];
                centerIndex = i;
            }
        }
        System.out.println(str.substring((centerIndex - maxLength) / 2 + 1, maxLength + 1));
        return maxLength - 1;
    }
}
