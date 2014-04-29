package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * @author: wgji
 * @date：2014年4月29日 下午4:47:33
 * @comment:
 */
public class TriLCS {
    char[] charA = null;
    char[] charB = null;
    char[] charC = null;

    public TriLCS(String sa, String sb, String sc) {
        charA = new char[sa.length() + 1];
        System.arraycopy(sa.toCharArray(), 0, charA, 1, sa.length());
        charB = new char[sb.length() + 1];
        System.arraycopy(sb.toCharArray(), 0, charB, 1, sb.length());
        charC = new char[sc.length() + 1];
        System.arraycopy(sc.toCharArray(), 0, charC, 1, sc.length());
    }

    public void getTriLCS() {
        int[][][] length = new int[charA.length][charB.length][charC.length];

        for (int a = 1; a < charA.length; a++)
            for (int b = 1; b < charB.length; b++)
                for (int c = 1; c < charC.length; c++) {
                    if (charA[a] == charB[b] && charA[a] == charC[c]) {
                        length[a][b][c] = length[a - 1][b - 1][c - 1] + 1;
                    } else if (charA[a] == charB[b] && charA[a] != charC[c]) {
                        length[a][b][c] = max(length[a - 1][b - 1][c], length[a][b][c - 1]);
                    } else if (charA[a] == charC[c] && charA[a] != charB[b]) {
                        length[a][b][c] = max(length[a - 1][b][c - 1], length[a][b - 1][c]);
                    } else if (charB[b] == charC[c] && charA[a] != charB[b]) {
                        length[a][b][c] = max(length[a][b - 1][c - 1], length[a - 1][b][c]);
                    } else {
                        length[a][b][c] = max(length[a - 1][b][c], length[a][b - 1][c], length[a][b][c - 1],
                                length[a - 1][b - 1][c], length[a - 1][b][c - 1], length[a][b - 1][c - 1]);
                    }
                }

        // 打印最长公共子序列
        String lcstr = "";
        int a = charA.length - 1;
        int b = charB.length - 1;
        int c = charC.length - 1;
        while (a >= 1 && b >= 1 && c >= 1) {
            if (charA[a] == charB[b] && charA[a] == charC[c]) {
                lcstr = charA[a] + lcstr;
                a--;
                b--;
                c--;
            } else if (charA[a] == charB[b] && charA[a] != charC[c]) {
                if (length[a - 1][b - 1][c] <= length[a][b][c - 1])
                    c--;
                else {
                    a--;
                    b--;
                }
            } else if (charA[a] == charC[c] && charA[a] != charB[b]) {
                if (length[a - 1][b][c - 1] <= length[a][b - 1][c])
                    b--;
                else {
                    a--;
                    c--;
                }
            } else if (charB[b] == charC[c] && charA[a] != charB[b]) {
                if (length[a][b - 1][c - 1] <= length[a - 1][b][c])
                    a--;
                else {
                    b--;
                    c--;
                }
            } else {
                int maxize = max(length[a - 1][b][c], length[a][b - 1][c], length[a][b][c - 1],
                        length[a - 1][b - 1][c], length[a - 1][b][c - 1], length[a][b - 1][c - 1]);
                if (maxize == length[a - 1][b][c])
                    a--;
                if (maxize == length[a][b - 1][c])
                    b--;
                if (maxize == length[a][b][c - 1])
                    c--;
                if (maxize == length[a - 1][b - 1][c]) {
                    a--;
                    b--;
                }
                if (maxize == length[a - 1][b][c - 1]) {
                    a--;
                    c--;
                }
                if (maxize == length[a][b - 1][c - 1]) {
                    b--;
                    c--;
                }
            }
        }

        System.out.println("最长子串为：" + lcstr + "(length=" + length[charA.length - 1][charB.length - 1][charC.length - 1]
                + ")");
    }

    /**
     * 取最大值
     */
    private int max(int m, int n) {
        return m > n ? m : n;
    }

    /**
     * 取最大值
     */
    private int max(int x, int y, int z, int k, int m, int n) {
        int maxizen = 0;
        if (maxizen < x)
            maxizen = x;
        if (maxizen < y)
            maxizen = y;
        if (maxizen < z)
            maxizen = z;
        if (maxizen < k)
            maxizen = k;
        if (maxizen < m)
            maxizen = m;
        if (maxizen < n)
            maxizen = n;
        return maxizen;
    }

    public static void main(String[] args) {
        TriLCS tri = new TriLCS("aadsbbbcs", "adsabcs", "adbsbsdcs");
        tri.getTriLCS();
    }
}
