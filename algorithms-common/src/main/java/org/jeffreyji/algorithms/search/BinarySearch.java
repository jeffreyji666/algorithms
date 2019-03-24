package org.jeffreyji.algorithms.search;

/**
 * 问题：给定一个数组，此数组先递增后递减，找出其中最大值位置。
 * <p>
 * 思路：先递增后递减的数组最大元素A[i]，此值最大的条件是A[i]>A[i-1]，同时A[i]>A[i+1]。
 * 此题目原型为有序数组查找问题，有序数组一般采用二分查找，但是需要注意退出条件。
 */
public class BinarySearch {
    //传入数组及其大小
    public static int binarySearch(int[] a) {
        if (null == a || a.length == 0)
            return -1;            //如果数组大小为0，则返回错误
        int begin = 0;
        int end = a.length - 1;
        int middle = begin + (begin + end) / 2;

        while (middle > 0 && middle < a.length - 1) {
            if (a[middle] > a[middle + 1] && a[middle] > a[middle - 1]) {  //如果符合条件就返回此值
                return middle;
            } else if (a[middle] < a[middle + 1]) {
                begin = middle;
            } else {
                end = middle;
            }
            middle = (end + begin) / 2;
        }

        if (middle == 0)
            return 0; //如果数组是完全递减的，则第一个值就是最大值
        if (middle == a.length - 1)
            return a.length - 1; //如果数组是完全递增的，则最后一个值为最大值
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        System.out.println(a[binarySearch(a)]);
    }
}
