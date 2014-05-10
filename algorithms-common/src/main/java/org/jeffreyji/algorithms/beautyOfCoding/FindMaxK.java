package org.jeffreyji.algorithms.beautyOfCoding;

/**
 * @author: wgji
 * @date：2014年5月10日 下午5:26:18
 * @comment:寻找最大的K个数
 */

public class FindMaxK {
    public static void main(String[] args) {
        int[] a = { 1, 3, 4, 7, 8, 5, 6 };
        int index = findMaxK(a, 0, a.length - 1, 3);
        for (int i = 0; i <= index; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
        buildMinHeap(a, 3);
        // 数组中最小元素在根a[1]
        for (int i = a.length - 1; i > 2; i--) {
            // 如果X比堆顶元素Y小，则不需要改变原来的堆
            // 如果X比堆顶元素Y大，那么用X替换堆顶元素Y，在替换之后，X可能破坏了最小堆的结构，需要调整堆来维持堆的性质
            int temp;
            if (a[0] < a[i]) {
                // 交换
                temp = a[i];
                a[i] = a[0];
                a[0] = temp;
                // 重新调整，保持最小堆的性质
                minHeap(a, 0, 2);
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.printf("%d ", a[i]);
        }
    }

    /* 将数组a[s]...a[t]中的元素用一个元素划开，保存中a[k]中 */
    public static int partition(int a[], int low, int high) {
        int index = a[low]; // 取划分元素
        int i = low; // 扫描指针初值
        int j = high;
        while (i < j) {
            while ((a[j] < index) && i < j) {
                j--; // 从右向左扫描,如果是比划分元素小，则不动
            }
            if (i < j) {
                a[i++] = a[j]; // 大元素向左边移
            }
            while ((a[i] >= index) && i < j) {
                i++; // 从左向右扫描，如果是比划分元素大，则不动
            }
            if (i < j) {
                a[j--] = a[i]; // 小元素向右边移
            }
        } // 直到指针i与j相等
        a[i] = index; // 划分元素就位
        return i;
    }

    /*
     * 查找数组前K个最大的元素，index:返回数组中最大元素中第K个元素的下标(从0开始编号), high为数组最大下标
     */
    public static int findMaxK(int a[], int low, int high, int k) {
        int index = -1;
        if (low < high) {
            index = partition(a, low, high);
            int len = index - low + 1; // 表示第几个位置
            if (len == k) {
                return index; // 返回第k个位置
            } else if (len < k) {
                index = findMaxK(a, index + 1, high, k - len);
            } else {
                index = findMaxK(a, low, index, k);
            }
        }
        return index;
    }

    // 调整以index为根的子树
    // k：堆中元素个数
    public static int minHeap(int a[], int index, int k) {
        int minIndex = index;
        // 左子节点
        int leftIndex = 2 * index;
        // 右子节点
        int rightIndex = 2 * index + 1;
        if (leftIndex <= k && a[leftIndex] < a[minIndex]) {
            minIndex = leftIndex;
        }
        if (rightIndex <= k && a[rightIndex] < a[minIndex]) {
            minIndex = rightIndex;
        }
        // 如果a[index]是最小的，则以index为根的子树已是最小堆否则index的子节点有最小元素
        // 则交换a[index],a[MinIndex],从而使index及子女满足堆性质
        int temp;
        if (minIndex != index) {
            // 交换a[index],a[MinIndex]
            temp = a[index];
            a[index] = a[minIndex];
            a[minIndex] = temp;
            // 重新调整以MinIndex为根的子树
            minHeap(a, minIndex, k);
        }
        return 0;
    }

    // 建堆：将一个数组a[1-k]变成一个最小堆
    public static int buildMinHeap(int a[], int k) {
        // 用容量为k的最小堆来存储最大的k个数
        for (int i = k; i >= 0; i--) {
            // 调整以i为根节点的树使之成为最小堆
            minHeap(a, i, k);
        }
        return 0;
    }
}
