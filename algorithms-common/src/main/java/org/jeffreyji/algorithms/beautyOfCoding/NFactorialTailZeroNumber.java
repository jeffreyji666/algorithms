package org.jeffreyji.algorithms.beautyOfCoding;

/**
 * @author: wgji
 * @date：2014年4月29日 下午6:04:34
 * @comment:1. 100的阶乘有几个结尾零
 * 2. N!的二进制表示中最低位1的位置,如果3的阶乘6表示为0110，最低位在第2位
 */
public class NFactorialTailZeroNumber {
    public static void main(String args[]) {
        System.out.println(trailingZeros(100));
        System.out.println(theLowestPositionOfOne(6));
        System.out.println(theLowestPositionOfOne2(6));
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
    
    //N!的二进制表示中最低位1的位置，即求N/2 + N/4 + N/8 + ...
    public static int theLowestPositionOfOne(int input){
        int res = 0;
        while(input > 0){
            input >>= 1;
            res += 1;
        }
        return res;
    }
    
    //也可以通过 N值减去N的二进制表示中1的数目
    public static int theLowestPositionOfOne2(int input){
        return input - CountOneDigit.count5(input);
    }
}
