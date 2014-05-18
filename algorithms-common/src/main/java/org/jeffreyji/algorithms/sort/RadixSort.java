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

    public static int[] radixSort(int[] ArrayToSort, int digit) {
        // low to high digit
        for (int k = 1; k <= digit; k++) {
            // temp array to store the sort result inside digit
            int[] tmpArray = new int[ArrayToSort.length];

            // temp array for countingsort
            int[] tmpCountingSortArray = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

            // CountingSort
            for (int i = 0; i < ArrayToSort.length; i++) {
                // split the specified digit from the element
                int tmpSplitDigit = ArrayToSort[i] / (int) Math.pow(10, k - 1)
                        - (ArrayToSort[i] / (int) Math.pow(10, k)) * 10;
                tmpCountingSortArray[tmpSplitDigit] += 1;
            }

            for (int m = 1; m < 10; m++) {
                tmpCountingSortArray[m] += tmpCountingSortArray[m - 1];
            }

            // output the value to result
            for (int n = ArrayToSort.length - 1; n >= 0; n--) {
                int tmpSplitDigit = ArrayToSort[n] / (int) Math.pow(10, k - 1)
                        - (ArrayToSort[n] / (int) Math.pow(10, k)) * 10;
                tmpArray[tmpCountingSortArray[tmpSplitDigit] - 1] = ArrayToSort[n];
                tmpCountingSortArray[tmpSplitDigit] -= 1;
            }

            // copy the digit-inside sort result to source array
            for (int p = 0; p < ArrayToSort.length; p++) {
                ArrayToSort[p] = tmpArray[p];
            }
        }

        return ArrayToSort;
    }
}
