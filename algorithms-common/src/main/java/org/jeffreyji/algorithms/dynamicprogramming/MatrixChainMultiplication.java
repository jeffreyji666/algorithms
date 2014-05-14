package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * @author: wgji
 * @date：2014年5月14日 上午10:47:28
 * @comment:矩阵链相乘
 * Input: p[] = {40, 20, 30, 10, 30}   
 * Output: 26000  
 * There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
 * Let the input 4 matrices be A, B, C and D.  The minimum number of 
 * multiplications are obtained by putting parenthesis in following way
 *  (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        int[] dm = { 40, 20, 30, 10,30 };
        int begin = 1;
        int end = dm.length - 1;
        System.out.println(matrixChainOrderRec(dm, begin, end));
        System.out.println(matrixChainOrderDP(dm, dm.length));
    }

    // Matrix Ai has dimension dm[i-1] x dm[i] for i = 1..n
    public static int matrixChainOrderRec(int[] dm, int begin, int end) {
        if (begin == end) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first and last matrix,
        // recursively calculate count of multiplications for each parenthesis
        // placement and return the minimum count
        for (int k = begin; k < end; k++) {
            int count = matrixChainOrderRec(dm, begin, k) + matrixChainOrderRec(dm, k + 1, end) + dm[begin - 1] * dm[k]
                    * dm[end];
            min = Math.min(min, count);
        }
        return min;
    }

    // Matrix Ai has dimension dm[i-1] x dm[i] for i = 1..n
    // Time Complexity: O(n^3), Auxiliary Space: O(n^2)
    public static int matrixChainOrderDP(int[] dm, int n) {
        /*
         * For simplicity of the program, one extra row and one extra column are allocated in dp[][]. 0th row and 0th
         * column of m[][] are not used
         */
        int[][] dp = new int[n + 1][n + 1];
        /*
         * dp[i,j] = Minimum number of scalar multiplications needed to compute the matrix A[i]A[i+1]...A[j] = A[i..j]
         * where dimension of A[i] is p[i-1] x p[i]
         */

        // cost is zero when multiplying one matrix.
        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        // L is chain length. L starts from 2
        for (int L = 2; L < n; L++) {
            for (int begin = 1; begin < n - L + 1; begin++) {
                int end = begin + L - 1;
                dp[begin][end] = Integer.MAX_VALUE;
                for (int k = begin; k <= end - 1; k++) {
                    // q = cost/scalar multiplications
                    int q = dp[begin][k] + dp[k + 1][end] + dm[begin - 1] * dm[k] * dm[end];
                    dp[begin][end] = Math.min(dp[begin][end], q);
                }
            }
        }
        return dp[1][n - 1];
    }

}
