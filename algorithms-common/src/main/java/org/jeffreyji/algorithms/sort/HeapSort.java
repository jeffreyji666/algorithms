package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月11日 上午11:47:10
 * @comment: 堆排序
 * 前提：堆的根节点的序号是1，并且满足最大堆属性。 堆是存放在数组中的，堆的大小要小于数组的大小。 
 * 注意下边个方法的参数是以1开始记得。时间复杂度：O(nlgn) 原地排序，不需要多余的空间。
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] source = { 11, 2, 3, 1, 15, 20, 0, 12 };
        heapSort(source);
        System.out.println(Arrays.toString(source));
    }
    /**
     * 最大堆排序算法，将source按最大堆排序算法由小到大排序
     * 
     * @param source
     */
    public static void heapSort(int[] source) {
        bulidMaxHeap(source);
        for (int i = source.length - 1; i > 0; i--) {
            int tmp = source[0];
            source[0] = source[i];
            source[i] = tmp;
            maxHeapify(source, 1, i);
        }
    }

    /**
     * 以第i个元素为根的子树保持最大树,终点为第heapSize个元素。
     * source中第heapSize个元素之后的元素不予理会。 即堆的大小为heapSize，
     * 从数组第一个元素到第heapSize个元素为止。
     * 
     * @param source
     * @param i 第i个元素，指的是从1开始的。因此后边在取数组元素的时候，都有-1.
     * @param heapSize
     */
    public static void maxHeapify(int[] source, int i, int heapSize) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;
        if (heapSize >= left && source[left - 1] > source[i - 1]) {
            largest = left;
        }
        if (heapSize >= right && source[right - 1] > source[largest - 1]) {
            largest = right;
        }
        if (largest != i) {
            int tmp = source[i - 1];
            source[i - 1] = source[largest - 1];
            source[largest - 1] = tmp;
            maxHeapify(source, largest, heapSize);
        }
    }

    /**
     * 构建最大堆树
     * 
     * @param source
     */
    public static void bulidMaxHeap(int[] source) {
        // beginFlag以后的都是叶子节点。
        int middle =source.length / 2;
        for (int i = middle; i >= 1; i--) {
            maxHeapify(source, i, source.length);
        }
    }
}
