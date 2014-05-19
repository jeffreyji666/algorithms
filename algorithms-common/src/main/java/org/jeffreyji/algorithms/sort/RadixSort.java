package org.jeffreyji.algorithms.sort;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月18日 下午4:36:51
 * @comment:radix sort
 */

public class RadixSort {
    public static void main(String[] args) {
        int[] ary = new int[] { 332, 653, 632, 755, 433, 722, 48 };

        int[] res = radixSort(ary, 3);
        System.out.println(Arrays.toString(res));
    }

    public static int[] radixSort(int[] a, int digit) {
        // low to high digit
        for (int k = 1; k <= digit; k++) {
            // temp array to store the sort result inside digit
            int[] tmp = new int[a.length];

            // temp array for countingsort
            int[] tmpCountingArray = new int[10];

            // CountingSort
            for (int i = 0; i < a.length; i++) {
                // split the specified digit from the element
                int tmpSplitDigit = a[i] / (int) Math.pow(10, k - 1) - (a[i] / (int) Math.pow(10, k)) * 10;
                tmpCountingArray[tmpSplitDigit] += 1;
            }

            for (int i = 1; i < tmpCountingArray.length; i++) {
                tmpCountingArray[i] += tmpCountingArray[i - 1];
            }

            // output the value to result
            for (int i = a.length - 1; i >= 0; i--) {
                int tmpSplitDigit = a[i] / (int) Math.pow(10, k - 1) - (a[i] / (int) Math.pow(10, k)) * 10;
                tmp[tmpCountingArray[tmpSplitDigit] - 1] = a[i];
                tmpCountingArray[tmpSplitDigit] -= 1;
            }

            // copy the digit-inside sort result to source array
            System.arraycopy(tmp, 0, a, 0, tmp.length);
        }

        return a;
    }
}
