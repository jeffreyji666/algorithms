package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;

/**
 * @author: wgji
 * @date：2014年5月13日 下午11:37:36
 * @comment:Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example, Given the following matrix:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
   You should return [1,2,3,6,9,8,7,4,5].
 */

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 11 }, { 2, 12 }, { 3, 13 }, { 4, 14 }, { 5, 15 }, { 6, 16 }, { 7, 17 }, { 8, 18 },
                { 9, 19 }, { 10, 20 } };
        ArrayList<Integer> res = spiralOrder(matrix);
        for (Integer item : res) {
            System.out.printf("%d,", item);
        }
    }

    public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int loop = (2 + bottom) / 2;
        for (int j = 0; j < loop; j++) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            if (top > bottom) {
                return res;
            }
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (left > right) {
                return res;
            }
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            if (top > bottom) {
                return res;
            }
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if (left > right) {
                return res;
            }
        }
        return res;
    }
}
