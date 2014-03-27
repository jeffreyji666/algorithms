package org.jeffreyji.algorithms;

/**
 * @author: wgji
 * @date：2014年3月27日 下午12:12:24
 * @comment:
 */
public class QuickSort {
    public static int[] x = { 3, 2, 4, 1, 6 };

    public static void main(String[] args) {
        quicksort(0, x.length - 1);
    }

    public static void quicksort(int l, int u) {
        int i, m;
        if (l >= u) {
            return;
        }
        m = l;
        for (i = l + 1; i <= u; i++) {
            if (x[i] < x[l]) { // buggy!
                swap(++m, i);
            }
        }
        printArray();
        System.out.println("before swap xl:" + x[l] + ", xm:" + x[m]);
        swap(l, m);
        printArray();

        System.out.println("----------------------");
        quicksort(l, m - 1);
        quicksort(m + 1, u);

    }

    public static void swap(int i, int j) {
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    public static void printArray() {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]);
        }
        System.out.println();
    }
}
