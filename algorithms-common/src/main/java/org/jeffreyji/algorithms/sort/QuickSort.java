package org.jeffreyji.algorithms.sort;

/**
 * @author: wgji
 * @date：2014年3月27日 下午12:12:24
 * @comment:
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] x = { 3, 2, 4, 1, 6 };
        quickSort1(x, 0, x.length - 1);
    }

    public static void quickSort1(int[] x, int l, int u) {
        int i, m;
        if (l >= u) {
            return;
        }
        m = l;
        for (i = l + 1; i <= u; i++) {
            if (x[i] < x[l]) { // buggy!
                swap(x, ++m, i);
            }
        }
        printArray(x);
        System.out.println("before swap xl:" + x[l] + ", xm:" + x[m]);
        swap(x, l, m);
        printArray(x);

        System.out.println("----------------------");
        quickSort1(x, l, m - 1);
        quickSort1(x, m + 1, u);

    }

    private static void swap(int[] x, int i, int j) {
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    private static void printArray(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]);
        }
        System.out.println();
    }

    private static int partition(int[] a, int left, int right) {
        int temp;

        temp = a[left];
        while (left < right) {
            while (left < right && a[right] >= temp)
                --right;
            a[left] = a[right];

            while (left < right && a[left] <= temp)
                ++left;
            a[right] = a[left];
        }
        a[left] = temp;

        printArray(a);

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
