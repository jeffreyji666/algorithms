package org.jeffreyji.algorithms.toutiao;

/**
 * @author jiwengang
 * @since 2019/3/27 下午7:23
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"
---------------------
作者：Pi_dan
来源：CSDN
原文：https://blog.csdn.net/qq_38595487/article/details/81565557
版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class FindKthPermuration {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];

        //因式分解需要的基数
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                factorial[i] = 1;
                continue;
            }
            factorial[i] = factorial[i - 1] * (i);
        }
        //1,1,2,6,24
        //1*0+1*1+2*2+6*3+24*4=119
        //而我们实际需要的数是：1、2、3、4、5，但他们的组合序列就相当于0、1、2、3、4的组合，只是各自加1而已。
        //二者的不同还在于，0-4的k的表是范围是从0-119，而我们的k是从1-120，所以变换关系是k-1。


        StringBuilder res = new StringBuilder();
        boolean[] used = new boolean[n];
        int i = n - 1;
        while (i >= 0) {
            int digit = (k - 1) / factorial[i];//变换关系k-1
            res.append(findKth(used, digit));//先取最高位的值
            k -= digit * factorial[i--];
        }

        return res.toString();
    }
    //再次强调下，数组是用的地址，而我们传递的对象就是普通的参数
    public int findKth(boolean[] used, int digit) {
        int res = -1;
        while (digit >= 0) {
            if (!used[++res]) { //从小到大的去取值，同时进行标记
                digit--;
            }
        }
        used[res] = true;
        return res + 1;//从0-4，变为1-5

    }
}
