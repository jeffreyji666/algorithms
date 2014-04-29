package org.jeffreyji.algorithms.dynamicprogramming;

/** 
 * @author:  wgji
 * @date：2014年4月29日 下午6:11:23 
 * @comment: 最长递增子序列-Longest Increasing Subsequence
 */
import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        int[] A = { 5, 3, 4, 8, 6, 7 };
        System.out.println(lis(A));
        System.out.println(lis2(A));
        System.out.println(lis3(A));
    }

    // Naive O(n2) DP solution
    // 看A[i]能接在哪些数字后面
    public static int lis(int[] A) {
        int[] dp = new int[A.length]; // 对每一个元素存储它的lis
        Arrays.fill(dp, 1); // 初始化，每个数字本身就是长度为1的lis

        // 对每一个数字求其LIS
        for (int i = 1; i < A.length; i++) {
            // 找出A[i]能接在哪些数字后面，
            // 如果可以接，找出接完后长度最大那个
            for (int j = i - 1; j >= 0; j--) {
                // 只有符合递增规律的才能接
                if (A[j] < A[i]) {
                    // 找出接完后长度最大那个
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        // 现在已经有每个数的LIS，找出最长的那个
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }

    // Use 2 max
    public static int lis2(int[] A) {
        int[] dp = new int[A.length];
        Arrays.fill(dp, 1);

        int max = 0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    // 可以灵活的用max函数代替if比较
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }

    // 看A[i]后面能接上哪些数字
    public static int lis3(int[] A) {
        int[] dp = new int[A.length]; // 对每一个元素存储它的lis
        Arrays.fill(dp, 1); // 初始化，每个数字本身就是长度为1的lis

        // 对每一个数字求其LIS
        for (int i = 0; i < A.length; i++) {
            // 看A[i]后面能接上哪些数字
            // 如果可以接，找出接完后长度最大那个
            for (int j = i + 1; j < A.length; j++) {
                // 只有符合递增规律的才能接
                if (A[j] > A[i]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        // 现在已经有每个数的LIS，找出最长的那个
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }
}
