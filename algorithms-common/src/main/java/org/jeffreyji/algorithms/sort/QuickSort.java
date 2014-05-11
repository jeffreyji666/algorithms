package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

import org.junit.Assert;

/**
 * @author: wgji
 * @date：2014年3月27日 下午12:12:24
 * @comment:
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] x = { 3, 2, 4, 1, 6 };
        int[] y = { 3, 2, 4, 1, 6 };
        quickSort1(x, 0, x.length - 1);
        System.out.println("*********************");
        quickSort2(y, 0, y.length - 1);
        Assert.assertArrayEquals(x, y);
    }

    public static void quickSort1(int[] x, int left, int right) {
        int i, middle;
        if (left >= right) {
            return;
        }
        middle = left;
        for (i = left + 1; i <= right; i++) {
            if (x[i] < x[left]) { // buggy!
                swap(x, ++middle, i);
            }
        }
        System.out.println(Arrays.toString(x));
        System.out.println("before swap xl:" + x[left] + ", xm:" + x[middle]);
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
