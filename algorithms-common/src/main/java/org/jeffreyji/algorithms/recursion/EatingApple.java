package org.jeffreyji.algorithms.recursion;

/**
 * @author: wgji
 * @date：2014年4月9日 下午8:59:09
 * @comment: 
 * 题目大意： 有n个苹果， 排成一行， 并且从左到右给他们编号：1，2，3，4，...，n 
 * 现在有个人分几轮吃掉这些苹果。 每轮都从第一个开始吃，不同的是第一轮每隔一个吃一个，
 *           第二轮每隔两个吃一个，第三轮每隔三个吃一个，.... 问最后一个被吃的苹果编号是多少。
 * 
 * 一个例子，假设有9个苹果： 1，2，3，4，5，6，7，8，9 第一轮过后： 2，4，6，8 第二轮过后：4，6 第三轮过后：6
 *
 *           因此最后一个吃点的是6.
 * 
 *           其中n <= 2000000.
 * 
 * 解答： 令函数 f(m, k)表示当前共m个苹果，其中每k个吃一个（也就是每隔k-1个吃一个），并且当前m个苹果编号是1，2，3，...，m 
 * 那么当前轮完成后，剩余苹果数为 t = m-(m-1)/k-1，考虑
 * f(t, k+1) 与 f(m, k)的关系， 我们假设每次f调用都是重新编号传递过来的苹果。
 */
public class EatingApple {
    public static void main(String[] args) {
        System.out.println(solve(9, 2));
        System.out.println(solve(100, 2));
    }

    private static int solve(int n, int k) {
        if (n == 1) {
            return 1;
        }
        int ret = solve(n - (n - 1) / k - 1, k + 1);
        return ret + (ret - 1) / (k - 1) + 1;
    }
}
