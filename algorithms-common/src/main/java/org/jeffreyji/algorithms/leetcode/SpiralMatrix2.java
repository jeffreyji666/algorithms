package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月12日 上午11:09:55 
 * @comment: Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example,
 * Given n = 3, You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */

public class SpiralMatrix2 {
    public static void main(String[] args) {
        int[][] res = generateMatrix(3);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.printf(res[i][j] > 9 ? "%d," : " %d,", res[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n < 1) {
            return res;
        }
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int loop = (n + 1) / 2;
        for (int i = 0, num = 1; i < loop; i++) {
            for (int j = left; j <= right; j++) {
                res[top][j] = num++;
            }
            top++;
            if (top > bottom) {
                return res;
            }
            for (int j = top; j <= bottom; j++) {
                res[j][right] = num++;
            }
            right--;
            if (left > right) {
                return res;
            }
            for (int j = right; j >= left; j--) {
                res[bottom][j] = num++;
            }
            bottom--;
            if (top > bottom) {
                return res;
            }
            for (int j = bottom; j >= top; j--) {
                res[j][left] = num++;
            }
            left++;
            if (left > right) {
                return res;
            }
        }
        
        return res;
    }
}
