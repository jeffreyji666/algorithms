package org.jeffreyji.algorithms.interviews; 

/** 
 * @author:  wgji
 * @date：2014年5月15日 上午11:24:04 
 * @comment:pinterest面试题,给一个矩阵如下：
 * a,b,c,d
 * e,f,g,h
 * i,j,k,l
 * m,n,o,p
 * 
 * 要求按如下方式打印:
 * a,f,k,p
 * b,g,l
 * c,h
 * d
 * e,j,o
 * i,n
 * m
 */

public class MatrixConverter {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4, 5 },
                           { 6, 7, 8, 9, 10 }, 
                           { 11,12,13,14,15 }, 
                           { 16,17,18,19,20 },
                           { 21,22,23,24,25 },};
        int[][] res = convertMatrix(matrix);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                if (res[i][j] != 0) {
                    System.out.printf("%d,", res[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static int[][] convertMatrix(int[][] matrix) {
        int[][] res = new int[matrix.length * 2 - 1][matrix.length];
        for (int len = 0; len < matrix.length; len++) {
            for (int i = 0; i < matrix.length - len; i++) {
                res[len][i] = matrix[i][i + len];
            }
        }
        for (int len = 1; len < matrix.length; len++) {
            for (int i = len; i < matrix.length; i++) {
                res[matrix.length + len - 1][i - len] = matrix[i][i - len];
            }
        }
        return res;
    }
}
 