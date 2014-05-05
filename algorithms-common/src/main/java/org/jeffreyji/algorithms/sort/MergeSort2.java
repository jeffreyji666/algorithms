package org.jeffreyji.algorithms.sort;

/** 
 * @author:  wgji
 * @date：2014年5月5日 上午10:28:46 
 * @comment: 
 */
import java.util.Arrays;

public class MergeSort2 {

    public static void main(String[] args) {

        int[] a = { 20, 1, 3, 8, 5, 9, 4, 25 };
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void mergeSort(int[] a, int first, int last) {
        int len = a.length;
        if (first >= last)
            return;
        if (first < last) {
            // int mid=(first+last)/2;
            // to avoid overflow,you should do it like this:
            int mid = (first & last) + ((first ^ last) >> 1);
            int[] temp = new int[len];
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            mergeTwoArray(temp, a, first, mid, last);
        }
    }

    public static void mergeTwoArray(int[] temp, int[] a, int first, int mid, int last) {
        int i = first;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= last) {
            if (a[i] < a[j]) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= last) {
            temp[k++] = a[j++];
        }
        // System.arraycopy(src, srcPos, dest, destPos, length);
        // at first,i write it like this :
        // System.arraycopy(temp, 0, a, 0, temp.length);
        // I can't get it right until I do it like this:
        System.arraycopy(temp, 0, a, first, last - first + 1);
        // System.arraycopy(temp, 0, a, first, k);//it also works
    }
}
