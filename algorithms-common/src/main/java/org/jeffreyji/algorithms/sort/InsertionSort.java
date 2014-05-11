package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月11日 下午1:32:35
 * @comment:插入排序
 */

public class InsertionSort {
    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                int tmp = a[i];
                int j = i;
                while (j > 0 && a[j - 1] > tmp) {
                    a[j] = a[j - 1];
                    j--;
                }
                a[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] x = { 6, 2, 4, 1, 5, 9 };
        insertionSort(x);
        System.out.println(Arrays.toString(x));
    }
}
