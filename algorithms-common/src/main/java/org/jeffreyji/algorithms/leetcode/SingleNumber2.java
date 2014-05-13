package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月13日 上午10:06:56
 * @comment:Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * 分析:
 * Single Number 比较简单，直接异或即可。这题除了一个数只有一个，其他的都有三个，不能直接异或了。
 * 但是本质上道理是相同的，其他数有两个的时候，用异或将这个数消除，那现在就得想办法将三个数消除。
 * 先简单来看，如果只有3个1，用ones记录MOD3之后为一的，twos记录MOD3之后余2的情况，threes是MOD3=0 
 * 1 1 1
 * ones 1 0 0 // 一个3n+1个一的时候为1
 * twos 0 1 0
 * // 一个3n+2个一的时候为1
 * threes 0 0 1
 */

public class SingleNumber2 {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 1, 1, 2, 2 };
        System.out.println(singleNumber(a));
    }

    /**
     * 用ones记录到当前计算的变量为止，二进制1出现“1次”（mod 3 之后的 1）的数位。
     * 用twos记录到当前计算的变量为止，二进制1出现“2次”（mod 3 之后的 2）的数位。
     * 当ones和twos中的某一位同时为1时表示二进制1出现3次，此时需要清零。
     * 即用二进制模拟三进制计算。最终ones记录的是最终结果。
     * @param a
     * @return
     */
    public static int singleNumber(int a[]) {
        int ones = 0, twos = 0, xthrees = 0;
        for (int i = 0; i < a.length; ++i) {
            twos |= (ones & a[i]);
            ones ^= a[i];
            xthrees = ~(ones & twos);
            ones &= xthrees;
            twos &= xthrees;
            System.out.printf("ones:%d,twos:%d,xthrees:%d\n", ones, twos, xthrees);
            System.out.printf("ones:%s,twos:%s,xthrees:%s\n", Integer.toBinaryString(ones),
                    Integer.toBinaryString(twos), Integer.toBinaryString(xthrees));
        }

        return ones;
    }
}
