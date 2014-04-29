package org.jeffreyji666.algorithms.backtracking;

/**
 * @author: wgji
 * @date：2014年4月29日 下午5:01:50
 * @comment: Sudoku Solver Write a program to solve a Sudoku puzzle by filling the empty cells. 
 * Empty cells are indicated by the character '.'. 
 * You may assume that there will be only one unique solution.
 */
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = { 
                { '.', '.', '9', '7', '4', '8', '.', '.', '.' },
                { '7', '.', '.', '.', '.', '.', '.', '.', '.' }, 
                { '.', '2', '.', '1', '.', '9', '.', '.', '.' },
                { '.', '.', '7', '.', '.', '.', '2', '4', '.' }, 
                { '.', '6', '4', '.', '1', '.', '5', '9', '.' },
                { '.', '9', '8', '.', '.', '.', '3', '.', '.' }, 
                { '.', '.', '.', '8', '.', '3', '.', '2', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '6' }, 
                { '.', '.', '.', '2', '7', '5', '9', '.', '.' }, };
        solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    public static boolean dfs(char[][] board, int x, int y) {
        if (x > 8 || y > 8) { // 全部深搜完毕
            return true;
        }
        if (board[x][y] == '.') { // 如果当前是空格
            for (int k = 1; k <= 9; k++) {
                if (isValid(board, x, y, (char) ('0' + k))) { // 说明在当前空格找到一个满足条件的数字
                    board[x][y] = (char) ('0' + k);
                    int nextX = x;
                    int nextY = y + 1;
                    if (nextY == 9) {
                        nextY = 0;
                        nextX++;
                    }
                    if (dfs(board, nextX, nextY)) { // 对下一个空格搜索数字，如果下一个位置找到满足条件的数字，就此返回。否则改变当前空格的数字继续测试
                        return true;
                    }
                    board[x][y] = '.';
                }
            }
            return false; // 对于当前空格，如果所有的数字都不满足，则无解！
        } else { // 如果当前已经有数字，就跳过继续深搜
            int nextX = x;
            int nextY = y + 1;
            if (nextY == 9) {
                nextY = 0;
                nextX++;
            }
            return dfs(board, nextX, nextY);
        }

    }

    public static boolean isValid(char[][] board, int x, int y, char k) {
        for (int i = 0; i < 9; i++) { // 同列检查
            if (board[i][y] == k) {
                return false;
            }
            if (board[x][i] == k) { // 同行检查
                return false;
            }
        }
        System.out.printf("--------%d,%d",x,y);
        System.out.println();
        for (int i = 0; i < 3; i++) { // 九宫格检查
            for (int j = 0; j < 3; j++) {
                System.out.printf("[%d][%d]", 3 * (x / 3) + i, 3 * (y / 3) + j);
                System.out.println();
                if (board[3 * (x / 3) + i][3 * (y / 3) + j] == k) {
                    return false;
                }
            }
        }
        return true;
    }

}
