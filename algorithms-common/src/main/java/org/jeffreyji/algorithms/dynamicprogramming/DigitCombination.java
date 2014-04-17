package org.jeffreyji.algorithms.dynamicprogramming; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/** 
 * @author:  wgji
 * @date：2014年4月17日 下午8:41:21 
 * @comment: 
 * 描述
0 - 6根火柴
1 - 2根火柴
2 - 5根火柴
3 - 5根火柴
4 - 4根火柴
5 - 5根火柴
6 - 6根火柴
7 - 3根火柴
8 - 7根火柴
9 - 6根火柴
如上图所示，我们可以用不同数量的火柴排成0~9这10个数字。现在这个问题需要让聪明的你来计算一下，
给你N根火柴，总共可以组合成多少个被M取余后结 果是素数的数字。
注意，N根火柴都要用上,另外，除了0，其他数字均不能出现前导零。
输入
第一行有一个正整数T，表示有T组测试数据。
接下来T行每一行表示一组测试数据，每行包含空格隔开的两个正整数N和M。
其中：
T < =20, 
N, M <=10000并且M*N<=1000000
输出
对于每一个测试数据，请输出一行，包含一个数，即结果对1000000007取模的值。
样例输入
4
4 15
20 4
6 10
6 20
样例输出
1
6982
1
2
 */

public class DigitCombination {
    static int n, m;
    static boolean[] isprime;
    static int[][] memo;
    static int x[] = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };

    static int dp(int c, int d) {
        if (c == 0 && isprime[d]) {
            return 1;
        }
        if (c < 0) {
            return 0;
        }
        if (memo[c][d] != -1) {
            return memo[c][d];
        }
        int ret = 0;
        for (int i = 0; i < 10; i++) {
            ret += dp(c - x[i], (d * 10 + i) % m);
            ret %= 1000000007;
        }
        memo[c][d] = ret;
        return ret;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(cin.readLine());
        while (t-- > 0) {
            String[] sa = cin.readLine().split(" ");
            n = Integer.parseInt(sa[0]);
            m = Integer.parseInt(sa[1]);
            isprime = getPrimes(m);

            memo = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    memo[i][j] = -1;
                }
            }
            int res = 0;
            for (int i = 1; i < 10; i++) {
                res += dp(n - x[i], i % m);
                res %= 1000000007;
            }
            out.println(res);
        }
        out.close();
    }

    public static boolean[] getPrimes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        int m = (int) Math.sqrt(n);
        for (int i = 2; i <= m; i++)
            if (prime[i])
                for (int k = i * i; k <= n; k += i)
                    prime[k] = false;
        return prime;
    }
}
 