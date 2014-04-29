package org.jeffreyji.algorithms.util;

/**
 * @author: wgji
 * @date：2014年4月29日 下午6:04:34
 * @comment: 100的阶乘有几个结尾零
 */
public class NFactorialTailZeroNumber {
    public static void main(String args[]) {
        System.out.println(trailingZeros(100));
    }

    // 只用计算5因子的个数即可，因为5因子的个数肯定小于2因子的个数
    public static int trailingZeros(int input) {
        int quotient;
        int fiveFactors = 0;

        quotient = input / 5;
        int i = 1;
        while (quotient != 0) {
            fiveFactors += quotient;
            i++;
            quotient = (int) (input / Math.pow(5, i));
        }

        return fiveFactors;
    }
}
