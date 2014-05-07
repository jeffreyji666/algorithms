package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月7日 下午2:05:10
 * @comment:Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */

public class SettingMatrixZeroes {
    public static void main(String[] args){
        int[][] matrix= {{1,0},{1,1}};
        setZeroes(matrix);
    }
    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean empty_row0 = false;
        boolean empty_col0 = false;
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                empty_row0 = true;
                break;
            }
        }

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                empty_col0 = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (empty_row0) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }

        if (empty_col0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
