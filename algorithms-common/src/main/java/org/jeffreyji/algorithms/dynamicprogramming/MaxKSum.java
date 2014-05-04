package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * @author: wgji
 * @date：2014年5月4日 上午11:24:20
 * @comment: 【题意】有n(<=200000)个数排成一列，求长度不超过k(1<=k<=n)的连续的子串的和的最大值. 
 * 【分析】 设dp[i]表示以i结尾的满足约束的最大子段和，sum[i]表示从0到第i个数的和，
 *  状态转移方程：dp[i]=sum[i]-min{sum[j]},max(0,i-k+1)<=j<i，dp[0]=0 最后结果为max{dp[i],1<=i<=n}.
 * 
 *  单纯二重循环O(n*k)肯定超时. 考虑求sum[j-1]的最小值 max(0,i-k)<=j<i,是否可以优化? 
 *  1.显然优先级队列可以适用,维护堆,时间复杂度优化为nlogk
 *  2.考虑求解的单调性,若i<j且sum[i]>sum[j],则i可以被舍弃.只要维护一个递增的单调队列即可!
 */
public class MaxKSum {
    public static void main(String[] args) {
        int[] a = { 6, -1, 2, -6, 5, -5 };
        int k = 3;
        int[] sum = new int[a.length + k];
        sum[0] = 0;
        for (int i = 1; i < a.length; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        for (int i = a.length; i < a.length + k; i++) {
            sum[i] = sum[i - 1] + a[i - a.length + 1];
        }
        int[] que = new int[a.length];
        solve(sum, que, k);
    }

    public static void solve(int[] sum, int[] que, int k) {
        int head = 1, tail = 0;
        int result = 0, st = 0, end = 0;
        for (int i = 1; i < sum.length; i++) {
            while (head <= tail && sum[i - 1] < sum[que[tail]]) {
                tail--;
            }
            while (head <= tail && que[head] < i - k) {
                head++;
            }
            tail++;
            que[tail] = i - 1;
            // output start
            if (sum[i] - sum[que[head]] > result) {
                result = sum[i] - sum[que[head]];
                st = que[head] + 1;
                end = i;
            }
            // output end
        }
        if (end > sum.length - k)
            end -= sum.length - k;
        System.out.printf("%d,%d,%d", result, st, end);
    }
}
