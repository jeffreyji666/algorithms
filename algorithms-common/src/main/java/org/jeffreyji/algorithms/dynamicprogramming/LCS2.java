package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * @author: wgji
 * @date：2014年4月29日 下午4:30:35
 * @comment: 假如我们有两个字符串：X=[0,1,2....n] Y=[0,1,2...m]。我们定义L(i, j)为X[0...i]与Y[0...j]之间的最长公共子序列的长度。
 *           通过动态规划思想(复杂问题的最优解是子问题的最优解和子问题的重叠性质决定的)。我们考虑这样两种情况：
 *            (1) 当X[i]=Y[j]时， L(i, j)=L(i-1, j-1)+1 。证明很简单。 
 *            (2) 当X[i]!=Y[j]时， 说明此事X[0...i]和Y[0...j]的最长公共子序列中绝对不可能同时含有X[i]和Y[j]。
 *           那么公共子序列可能以X[i]结尾，可能以Y[j]结尾，可以末尾都不含有X[i]或Y[j]。因此 L(i, j)= MAX{L(i-1 , j), L(i, j-1)}
 */
public class LCS2 {
    /** 字符串X的字符数组 */
    private char[] charArrayX = null;
    /** 字符串Y的字符数组 */
    private char[] charArrayY = null;

    public LCS2(String sa, String sb) {
        charArrayX = new char[sa.length() + 1];
        System.arraycopy(sa.toCharArray(), 0, charArrayX, 1, sa.length());
        charArrayY = new char[sb.length() + 1];
        System.arraycopy(sb.toCharArray(), 0, charArrayY, 1, sb.length());
    }

    /**
     * 得到最长公共子序列的长度
     */
    public void getLCS() {
        int[][] length = new int[charArrayX.length + 1][charArrayY.length + 1];

        for (int m = 1; m < charArrayX.length; m++) {
            for (int n = 1; n < charArrayY.length; n++) {
                if (charArrayX[m] == charArrayY[n]) {
                    length[m][n] = length[m - 1][n - 1] + 1;
                } else
                    length[m][n] = max(length[m - 1][n], length[m][n - 1]);
                // length[m][n] = max(length[m][n], length[m-1][n-1]);
            }
        }

        for (int m = 0; m < charArrayX.length; m++) {
            for (int n = 0; n < charArrayY.length; n++) {
                System.out.print(length[m][n] + " ");
            }
            System.out.println();
        }

        // 打印最长公共子序列
        String lcstr = "";
        int x = charArrayX.length - 1;
        int y = charArrayY.length - 1;
        while (x >= 1 && y >= 1) {
            if (charArrayX[x] == charArrayY[y]) {
                lcstr = charArrayX[x] + lcstr;
                x--;
                y--;
            } else {
                if (length[x - 1][y] <= length[x][y - 1]) // 往值较大的路径走
                    y--;
                else
                    x--;
            }
        }
        System.out.println("最长公共子序列为：" + lcstr + " [length=" + lcstr.length() + "]");
    }

    /**
     * 取最大值
     */
    private int max(int m, int n) {
        return m > n ? m : n;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        LCS2 lcs = new LCS2("GTTCCTAATA", "CGATAATTGAGA");
        lcs.getLCS();
    }
}
