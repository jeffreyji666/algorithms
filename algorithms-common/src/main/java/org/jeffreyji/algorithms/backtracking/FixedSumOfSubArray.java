package org.jeffreyji.algorithms.backtracking;

/**
 * @author: wgji
 * @date：2014年3月27日 下午6:04:36
 * @comment:给定一个数组，给出和为某值的子数组， 如给出数组3, 2, 4, 1, 6，和为7的子数组为：{3，4},{2,4,1},{1,6} 利用回溯算法解决
 */
public class FixedSumOfSubArray {

    private static boolean[] flag = new boolean[100];
    private static int count = 0;

    public static void main(String[] args) {
        int[] x = {-1, 0, 1, 2, -1, -4};
        getCombination(x, 0, 0);
        System.out.println(count);
    }

    /**
     * @param t 已经存储的元素个数
     */
    public static void getCombination(int[] a, int t, int sum) {
        count++;
        System.out.printf("getCombination:%b,%d,%d", flag[t > 0 ? t - 1 : t], t, sum);
        System.out.println();
        if (sum == 0) {
            for (int i = 0; i < t; i++) {
                if (flag[i]) {
                    System.out.printf("%d,", a[i]);
                }
            }
            System.out.println();
        } else {
            if (t == a.length) {
                return;
            } else {
                flag[t] = true;
                if (sum - a[t] >= 0) {
                    getCombination(a, t + 1, sum - a[t]);
                }
                flag[t] = false;
                if (sum >= 0) {
                    getCombination(a, t + 1, sum);
                }
            }
        }
    }
}
