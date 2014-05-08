package org.jeffreyji.algorithms.dynamicprogramming;

/** 
 * @author:  wgji
 * @date：2014年4月29日 下午6:11:23 
 * @comment: 最长递增子序列-Longest Increasing Subsequence
 */
import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        int[] a = { 1, 3, -1, -3, 10 };
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "," + Arrays.binarySearch(a, a[i]) + "," + binary(a, 0, a.length - 1, a[i]) + ","
                    + binarySearch(a, 0, a.length - 1, a[i]));
        }
        System.out.println("-----");
        
        int[] b = { 1, 3, -1, -3, 10 };
        
        
        System.out.println(lis(b));
        System.out.println(lis2(b));
        System.out.println(lis3(b));
        System.out.println(lis5(b));
        System.out.println(lis4(b));
    }

    // Naive O(n2) DP solution
    // 看A[i]能接在哪些数字后面
    public static int lis(int[] a) {
        int[] dp = new int[a.length]; // 对每一个元素存储它的lis

        // 对每一个数字求其LIS
        for (int i = 1; i < a.length; i++) {
            dp[i] = 1;
            // 找出A[i]能接在哪些数字后面，
            // 如果可以接，找出接完后长度最大那个
            for (int j = i - 1; j >= 0; j--) {
                // 只有符合递增规律的才能接
                if (a[j] < a[i]) {
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
    public static int lis2(int[] a) {
        int[] dp = new int[a.length];

        int max = 0;
        for (int i = 1; i < a.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    // 看A[i]后面能接上哪些数字
    public static int lis3(int[] a) {
        int[] dp = new int[a.length]; // 对每一个元素存储它的lis
        Arrays.fill(dp, 1); // 初始化，每个数字本身就是长度为1的lis

        // 对每一个数字求其LIS
        for (int i = 0; i < a.length; i++) {
            // 看A[i]后面能接上哪些数字
            // 如果可以接，找出接完后长度最大那个
            for (int j = i + 1; j < a.length; j++) {
                // 只有符合递增规律的才能接
                if (a[j] > a[i]) {
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

    /**
     * 最长递增子序列O(nlogn)算法： 状态转移方程：f[i] = max{f[i],f[j]+1},1<=j<i,a[j]<a[i]. 
     * 分析：加入x<y,f[x]>=f[y],则x相对于y更有潜力。
     * 首先根据f[]值分类，记录满足f[t]=k的最小的值a[t],记d[k]=min{a[t]},f[t]=k. 
     * 1. 发现d[k]在计算过程中单调不上升 
     * 2.d[1]<d[2]<...<d[k] (反证) 1 2 3 8 4 7
     * 解法： 1. 设当前最长递增子序列为len,考虑元素a[i]; 
     * 2. 若d[len]<a[i],则len++，并将d[len]=a[i];
     * 否则,在d[0-len]中二分查找,找到第一个比它小的元素d[k],并d[k+1]=a[i].
     */
    public static int lis4(int[] a) {
        System.out.println(Arrays.toString(a));
        int j;
        int[] d = new int[a.length + 1];
        d[0] = Integer.MIN_VALUE;
        d[1] = a[0];
        int len = 1; // 递增子序列长度
        for (int i = 1; i < a.length; i++) {
            if (d[len] < a[i]) {
                j = ++len;
            } else {
                j = binarySearch(d, 0, len, a[i]) + 1;
            }
            d[j] = a[i];
            System.out.println(Arrays.toString(d));
        }
        return len;
    }
    
    private static int binary(int[] d, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;

            if (d[mid] < key)
                low = mid + 1;
            else if (d[mid] > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return 0;
    }
    
    private static int binarySearch(int[] d, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key > d[mid] && key <= d[mid + 1])
                return mid;
            else if (key > d[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return 0;
    } 
    
    public static int lis5(int a[]) {
        /**
         * 定义一个数组，数组中元素B[i]是在<code>array</code>以B[i]元素为结尾数字的当前子数字序列长度为i的最小的数字。
         * 此数组有个性质，就是单调递增。
         */
        int b[] = new int[a.length + 1];

        /**
         * 当前所求得的最大子序列长度
         */
        int length = 1;
        b[0] = -10000000;
        /**
         * 初始化 子序列长度为1的当前最小数字肯定是array[0]
         */
        b[1] = a[0];

        for (int i = 1; i < a.length; i++) {
            int head, tail, median;
            head = 0;
            tail = length;
            /**
             * 查找array[i]这一数字在B数组中应该存放的位置
             */
            while (head <= tail) {
                median = (head + tail) / 2;
                if (b[median] < a[i]) {
                    head = median + 1;
                } else {
                    tail = median - 1;
                }
            }
            b[head] = a[i];
            /**
             * 如果array[i]存放的位置的index比当前所得的length还大的话， 说明已经找到更长的子序列，则长度等于该index
             */
            if (head > length)
                length = head;
            System.out.println(Arrays.toString(b));
        }
        return length;
    }
}
