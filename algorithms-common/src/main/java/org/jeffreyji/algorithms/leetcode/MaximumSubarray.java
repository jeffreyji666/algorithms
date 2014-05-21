package org.jeffreyji.algorithms.leetcode;


/**
 * @author: wgji
 * @date：2014年5月21日 下午6:39:33
 * @comment:Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] a = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(maxSubArray4(a));
    }

    /**
     * direct to compute every continues subarry sum and get the max
     * @param a
     * @return
     */
    public static int maxSubArray(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            int sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                max = max > sum ? max : sum;
            }
        }
        return max;
    }
    
    /**
     * the best solution
     * @param a
     * @return
     */
    public static int maxSubArray3(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < a.length; ++i) {
            if (sum < 0) {
                sum = a[i];
            } else {
                sum += a[i];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * dynamic programming
     * @param a
     * @return
     */
    public static int maxSubArray4(int a[]) {
        int[] c = new int[a.length];
        c[0] = a[0];
        int start = 0;
        int end = 0;
        int temp = 0;
        int maxGreatSum = Integer.MIN_VALUE;
        for (int i = 1; i < a.length; i++) {
            if (c[i - 1] <= 0) {
                c[i] = a[i];
                temp = i;
            } else {
                c[i] = a[i] + c[i - 1];
            }
            if (c[i] > maxGreatSum) {
                maxGreatSum = c[i];
                start = temp;
                end = i;
            }
        }
        System.out.printf("begin:%d,end:%d\n", start, end);
        return maxGreatSum;
    }

    static int Max3(int a, int b, int c) {
        return a > b ? a > c ? a : c : b > c ? b : c;
    }

    public static int maxSubSum(int a[], int left, int right) {
        int maxLeftSum, maxRightSum;
        int maxLeftBorderSum, maxRightBorderSum;
        int leftBorderSum, rightBorderSum;
        int center, i;

        if (left == right) /* Base case */
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        center = (left + right) / 2;
        maxLeftSum = maxSubSum(a, left, center);
        maxRightSum = maxSubSum(a, center + 1, right);

        maxLeftBorderSum = 0;
        leftBorderSum = 0;
        for (i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        maxRightBorderSum = 0;
        rightBorderSum = 0;
        for (i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return Max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    public static int maxSubArray5(int a[]) {
        return maxSubSum(a, 0, a.length - 1);
    }
}
