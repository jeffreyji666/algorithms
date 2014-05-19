package org.jeffreyji.algorithms.interviews; 

/** 
 * @author:  wgji
 * @date：2014年5月15日 上午11:24:04 
 * @comment:
 */

public class MatrixConverter {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2,  3, 4 },
                           { 5, 6,  7, 8 }, 
                           { 9, 10, 11,12 }, 
                           { 13,14, 15,16 }};
        int[][] res = convertMatrix(matrix);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                if (res[i][j] != 0) {
                    System.out.printf("%d ", res[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * pinterest面试题,二维数组 对角线输出 两个方向 
    例如对于数组： 
    { 1,  2,  3,  4 },  
    { 5,  6,  7,  8 }, 
    { 9, 10, 11, 12 },  
    { 13,14, 15, 16 }, 
     
    输出： 
    1 6 11 16 
    2 7 12 
    3 8 
    4 
    5 10 15 
    9 14 
    13 
     *
     * @param matrix
     * @return
     */
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
    
    /** 
    二维数组 对角线输出 两个方向 
    例如对于数组： 
    { 1,  2,  3,  4 },  
    { 5,  6,  7,  8 }, 
    { 9, 10, 11, 12 },  
    { 13,14, 15, 16 }, 
     
    slash方向输出： 
    1  
    5 2  
    9 6 3  
    13 10 7 4  
    14 11 8  
    15 12  
    16 
    */
    public static int[][] slashConvertMatrix(int[][] matrix){
        int[][] res = new int[matrix.length * 2 - 1][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i, k = 0; j >= 0 && k <= i; j--, k++) {
                res[i][k] = matrix[j][k];
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = matrix.length - 1, k = i; j >= i && k < matrix.length; j--, k++) {
                res[matrix.length + i - 1][matrix.length - 1 - j] = matrix[j][k];
            }
        }
        return res;
    }
    
    /**
     * 二维数组 对角线输出 两个方向 
    例如对于数组： 
    { 1,  2,  3,  4 },  
    { 5,  6,  7,  8 }, 
    { 9, 10, 11, 12 },  
    { 13,14, 15, 16 }, 
     * backslash输出： 
    4  
    3 8  
    2 7 12  
    1 6 11 16  
    5 10 15  
    9 14  
    13  
    数组的行数和列数可以不相等，但同一行的元素个数必须相等，称为"矩阵"更合适 
     * @param matrix
     * @return
     */
    public static int[][] backSlashConvertMatrix(int[][] matrix){
        int[][] res = new int[matrix.length * 2 - 1][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i, k = 0; j >= 0 && k < matrix.length; j--, k++) {
                res[i][k] = matrix[k][matrix.length - 1 - j];
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = i, k = 0; j < matrix.length && k < matrix.length; j++, k++) {
                res[matrix.length + i - 1][k] = matrix[j][k];
            }
        }
        return res;
    }
}
 