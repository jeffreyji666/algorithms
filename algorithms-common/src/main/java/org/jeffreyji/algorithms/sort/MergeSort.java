package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月2日 下午12:23:16
 * @comment:
 */
public class MergeSort {
    public static void main(String[] args) {
        int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
                25, 53, 51 };
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] data, int left, int right) {
        if (left < right) {
            // 找出中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            mergeSort(data, left, center);
            // 对右边数组进行递归
            mergeSort(data, center + 1, right);
            // 合并
            merge(data, left, center, right);
        }
    }

    public static void merge(int[] data, int left, int center, int right) {
        int[] res = new int[data.length];
        int mid = center + 1;
        // third记录中间数组的索引
        int i = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小的放入中间数组
            if (data[left] <= data[mid]) {
                res[i++] = data[left++];
            } else {
                res[i++] = data[mid++];
            }
        }
        // 剩余部分依次放入中间数组
        while (mid <= right) {
            res[i++] = data[mid++];
        }
        while (left <= center) {
            res[i++] = data[left++];
        }
        // 将中间数组中的内容复制回原数组
        while (tmp <= right) {
            data[tmp] = res[tmp++];
        }
    }
}
