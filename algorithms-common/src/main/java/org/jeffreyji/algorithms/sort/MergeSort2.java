package org.jeffreyji.algorithms.sort;

/** 
 * @author:  wgji
 * @date：2014年5月5日 上午10:28:46 
 * @comment: 
 */
import java.util.Arrays;

public class MergeSort2 {

    public static void main(String[] args) {
        int first = 10, last = 13;
        System.out.println((first & last));
        System.out.println((first ^ last) >> 1);
        System.out.println((first & last) + ((first ^ last) >> 1));

        int[] a = { -1, -2, 20, 1, 3, 8, 5, 9, 4, 25 };
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] a, int first, int last) {
        if (first < last) {
            // int mid=(first+last)/2;
            // to avoid overflow,you should do it like this:
            int mid = (first & last) + ((first ^ last) >> 1);
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }
    }

    public static void merge(int[] a, int first, int mid, int last) {
        int[] temp = new int[a.length];
        int i = first;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= last) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= last) {
            temp[k++] = a[j++];
        }
        // System.arraycopy(temp, 0, a, first, last - first + 1);
        System.arraycopy(temp, 0, a, first, k);// it also works
    }
}
