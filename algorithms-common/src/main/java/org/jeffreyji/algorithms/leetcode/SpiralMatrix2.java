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
        int[][] res = generateMatrix(4);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.printf("%d,", res[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        if (n < 1)
            return result;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int loop = (n + 1) / 2;
        for (int i = 0, num = 1; i < loop; i++) {
            for (int j = left; j <= right; j++) {
                result[top][j] = num++;
            }
            top++;
            if (top > bottom)
                return result;
            for (int j = top; j <= bottom; j++) {
                result[j][right] = num++;
            }
            right--;
            if (left > right)
                return result;
            for (int j = right; j >= left; j--) {
                result[bottom][j] = num++;
            }
            bottom--;
            if (top > bottom)
                return result;
            for (int j = bottom; j >= top; j--) {
                result[j][left] = num++;
            }
            left++;
        }
        return result;
    }
}
