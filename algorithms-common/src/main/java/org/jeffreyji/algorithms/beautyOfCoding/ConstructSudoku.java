package org.jeffreyji.algorithms.beautyOfCoding;

import java.util.Random;

/**
 * @author: wgji
 * @date：2014年5月6日 上午10:24:31
 * @comment:
 */

public class ConstructSudoku {
    public static void main(String[] args) {
        int[][] sodoku = constructSudoku();
        for (int i = 0; i < sodoku.length; i++) {
            for (int j = 0; j < sodoku[i].length; j++) {
                System.out.printf("%d ", sodoku[i][j]);
            }
            System.out.println();
        }
    }

    public static int[] Random1to9() {
        Random random = new Random();
        int b[] = new int[9];
        for (int i = 0; i < 9; i++) {
            int t = random.nextInt(10);
            for (int j = 0; j < b.length; j++) {
                if (t == b[j]) {
                    t = random.nextInt(10);
                    j = -1;
                }
            }
            b[i] = t;
        }
        return b;
    }

    public static int[][] constructSudoku() {
        int[][] a = new int[9][9];
        int[] b = Random1to9();
        for (int i = 3; i < 6; i++)
            for (int j = 3; j < 6; j++) {
                a[i][j] = b[(i - 3) * 3 + j - 3];// 产生中间3*3的初始数
                // 产生中间左侧的3*3方格中的数，注意左侧和右侧可以互换
                if (i < 5) {
                    a[i + 1][j - 3] = a[i][j];
                } else {
                    a[i - 2][j - 3] = a[i][j];
                }
                // 产生中间右侧的3*3方格中的数
                if (i < 4) {
                    a[i + 2][j + 3] = a[i][j];
                } else {
                    a[i - 1][j + 3] = a[i][j];
                }
            }
        for (int j = 0; j < 3; j++)
            for (int i = 3; i < 6; i++) {
                // 产生左上角、中间最上层和右上角3*3方格中的数，左上角和左下角可以互换，中间最上层和中间最下层可以互换，右上角和右下角可以互换
                if (j < 2) {
                    a[i - 3][j + 1] = a[i][j];
                    a[i - 3][j + 4] = a[i][j + 3];
                    a[i - 3][j + 7] = a[i][j + 6];
                } else {
                    a[i - 3][j - 2] = a[i][j];
                    a[i - 3][j + 1] = a[i][j + 3];
                    a[i - 3][j + 4] = a[i][j + 6];
                }
                // 产生左下角、中间最下层和右下角3*3方格中的数
                if (j < 1) {
                    a[i + 3][j + 2] = a[i][j];
                    a[i + 3][j + 5] = a[i][j + 3];
                    a[i + 3][j + 8] = a[i][j + 6];
                } else {
                    a[i + 3][j - 1] = a[i][j];
                    a[i + 3][j + 2] = a[i][j + 3];
                    a[i + 3][j + 5] = a[i][j + 6];
                }
            }
        return a;
    }
}
