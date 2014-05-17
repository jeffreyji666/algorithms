package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月17日 下午6:12:53
 * @comment:
 */

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5 };
        int[] b = { 2, 3, 5, 6, 7 };

        System.out.println(findMedianSortedArrays(a, b));
    }

    public static double findMedianSortedArrays(int a[], int b[]) {
        int m = a.length;
        int n = b.length;

        if ((m + n) % 2 != 0) // odd
            return (double) findKth(a, b, (m + n) / 2, 0, m - 1, 0, n - 1);
        else { // even
            return (findKth(a, b, (m + n) / 2, 0, m - 1, 0, n - 1) + findKth(a, b, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
        }
    }

    public static int findKth(int a[], int b[], int k, int aStart, int aEnd, int bStart, int bEnd) {

        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        // Handle special cases
        if (aLen == 0)
            return b[bStart + k];
        if (bLen == 0)
            return a[aStart + k];
        if (k == 0)
            return a[aStart] < b[bStart] ? a[aStart] : b[bStart];

        int aMid = aLen * k / (aLen + bLen); // a's middle count
        int bMid = k - aMid - 1; // b's middle count

        // make aMid and bMid to be array index
        aMid = aMid + aStart;
        bMid = bMid + bStart;

        if (a[aMid] > b[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }

        return findKth(a, b, k, aStart, aEnd, bStart, bEnd);
    }
}
