package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年3月27日 下午12:12:24
 * @comment:
 */
public class QuickSort {

    public static void main(String[] args) {
//        int[] x = { 2, 8, 7, 1, 3, 5, 6, 4 };
//        quickSort1(x, 0, x.length - 1);
//        System.out.println("*********************");

        int[] y = {10, 5, 3, 2, 4, 1, 6, 0, -1, 8};
        quickSort2(y, 0, y.length - 1);
    }

    public static void quickSort1(int[] x, int left, int right) {
        System.out.println(Arrays.toString(x));
        if (left >= right) {
            return;
        }
        int middle = left;
        for (int i = left + 1; i <= right; i++) {
            if (x[i] < x[left]) { // buggy!
                System.out.println("before swap:" + Arrays.toString(x));
                swap(x, ++middle, i);
                System.out.println("after swap:" + Arrays.toString(x));
            }
        }
        swap(x, left, middle);
        System.out.println(Arrays.toString(x));

        System.out.println("----------------------");
        quickSort1(x, left, middle - 1);
        quickSort1(x, middle + 1, right);

    }

    private static void swap(int[] x, int i, int j) {
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    private static int partition(int[] a, int left, int right) {
        int temp = a[left];
        while (left < right) {
            while (left < right && a[right] >= temp) {
                --right;
            }
            a[left] = a[right];

            while (left < right && a[left] <= temp) {
                ++left;
            }
            a[right] = a[left];
        }
        a[left] = temp;

        System.out.println(Arrays.toString(a));
        System.out.println("left:" + left + ",a[left]:" + a[left]);
        return left;
    }

    public static void quickSort2(int[] a, int left, int right) {
        if (left >= right)
            return;

        int pivot = partition(a, left, right);
        quickSort2(a, left, pivot - 1);
        quickSort2(a, pivot + 1, right);
    }
}
