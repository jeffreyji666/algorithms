package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月11日 下午1:32:35
 * @comment:插入排序
 */

public class InsertionSort {
    public static void insertionSort(int[] unsorted) {
        for (int i = 1; i < unsorted.length; i++) {
            if (unsorted[i - 1] > unsorted[i]) {
                int temp = unsorted[i];
                int j = i;
                while (j > 0 && unsorted[j - 1] > temp) {
                    unsorted[j] = unsorted[j - 1];
                    j--;
                }
                unsorted[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] x = { 6, 2, 4, 1, 5, 9 };
        insertionSort(x);
        System.out.println(Arrays.toString(x));
    }
}
