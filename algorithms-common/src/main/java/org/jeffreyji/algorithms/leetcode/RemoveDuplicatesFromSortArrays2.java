package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月23日 下午11:44:48 
 * @comment: Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 */

public class RemoveDuplicatesFromSortArrays2 {

    public static void main(String[] args) {
        int[] a = {1,1,1,2,2,3,3,3};
        int length = removeDuplicates(a,a.length);
        for(int i= 0 ; i < length; i++){
            System.out.printf("%d,",a[i]);
        }
    }

    public static int removeDuplicates(int a[], int n) {
        if (n <= 2) {
            return n; // no need to deal with n<=2 case
        }
        int len = 2, itor = 2;
        while (itor < n) {
            if (a[itor] != a[len - 2]) {
                a[len++] = a[itor];
            }
            itor++;
        }
        return len;
    }
}
 