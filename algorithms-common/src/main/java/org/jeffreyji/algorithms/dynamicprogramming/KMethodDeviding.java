package org.jeffreyji.algorithms.dynamicprogramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author: wgji
 * @date：2014年4月9日 下午6:05:46
 * @comment:题目：
给定一个整数m(1<=m<=220)， 给定一个整数n (1<=n<=10 && n<=m), 要求把m恰好分成n个正整数的和， 显然可能存在多种分法。
每种分法记做一个向量， 然后排序所有向量，求第k个向量是什么？
例如： m=10, n=4, k=3
{1,1,1,7}
{1,1,2,6}
{1,1,3,5}  //这个是要求的
.....

思路：
首先枚举向量的第一位，假设是1， 然后假设我们能够算出 m-1， 分成n-1个数，且第一个数 >=1 (就是我们枚举的这个1)的总方法数为d， 
那么如果d 大于等于k， 那么显然原始问题的解的第一个数就是1(这里完后就是递归算后续的位)，  
如果d小于k，那么第一位不可能是1， 我们考虑2， 现在面对 k-d 作为新的k。
还有点要注意的是， 算出 m-1， 分成n-1个数，且第一个数 >=1 (就是我们枚举的这个1)的总方法数为d
这里要用dp， 否则重复计算很多。
 */
public class KMethodDeviding {
    private static int m, n, k;
    private static int[] result;

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(System.out);
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(cin.readLine());
        while (testcase-- > 0) {
            String[] sa = cin.readLine().split(" ");
            m = Integer.parseInt(sa[0]);
            n = Integer.parseInt(sa[1]);
            k = Integer.parseInt(sa[2]);
            memo = new int[m + 1][m + 1][n + 1];
            vst = new boolean[m + 1][m + 1][n + 1];
            result = new int[n];
            dfs(1, 0, m, k);
            for (int i = 0; i < result.length; i++) {
                out.print(result[i]+",");
            }
            out.println();
        }
        out.close();
    }

    private static void dfs(int base, int currentIndex, int remainingSum, int remainingKth) {
        System.out.println("dfs(" + base + "," + currentIndex + "," + remainingSum + "," + remainingKth + ")");
        if (currentIndex == n) {
            return;
        }
        for (int i = base; i <= remainingSum; i++) {
            int d = dp(remainingSum - i, i, n - currentIndex - 1);
            System.out.println("--------" + d);
            if (remainingKth <= d) {
                result[currentIndex] = i;
                dfs(i, currentIndex + 1, remainingSum - i, remainingKth);
                break;
            } else {
                remainingKth -= d;
            }
        }

    }

    private static int[][][] memo;
    private static boolean[][][] vst;

    private static int dp(int remainingSum, int base, int numberOfParts) {
        System.out.println("dp(" + remainingSum + "," + base + "," + numberOfParts + ")");
        if (numberOfParts == 0) {
            if (remainingSum == 0) {
                return 1;
            }
            return 0;
        }
        if (remainingSum < base) {
            return 0;
        }
        if (vst[remainingSum][base][numberOfParts]) {
            return memo[remainingSum][base][numberOfParts];
        }
        vst[remainingSum][base][numberOfParts] = true;
        int ret = 0;
        for (int i = base; i <= remainingSum; i++) {
            ret += dp(remainingSum - i, i, numberOfParts - 1);
            System.out.println("*******" + ret);
        }
        memo[remainingSum][base][numberOfParts] = ret;
        return ret;
    }
}
