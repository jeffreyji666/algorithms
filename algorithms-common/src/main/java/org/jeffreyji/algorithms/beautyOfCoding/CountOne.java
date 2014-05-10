package org.jeffreyji.algorithms.beautyOfCoding; 

/** 
 * @author:  wgji
 * @date：2014年5月10日 下午2:50:00 
 * @comment: 给定一个十进制整数N,求出从1到N的所有整数中出现”1”的个数。 
 * 例如：N=2，1,2出现了1个“1”。
 * N=12，1,2,3,4,5,6,7,8,9,10,11,12。出现了5个“1”。
 */

public class CountOne {
    /**
     * 任意一个n位数中“1”的个位可以分解为两个n-1位数中“1”的个数的和加上一个与最高位数相关的常数C。
     * 例如，f(12) = f(10 - 1) + f(12 - 10) + 3,其中3是表示最高位为1的数字个数，这里就是10,11,12；
     * f(132)=f(100 -1) + f(132 - 100) + 33，33代表最高位为1的数字的个数，这里就是100~132；
     * f(232) = 2*f(100 - 1) + f(32) + 100，因为232大于199，所以它包括了所有最高位为1的数字即100~199，共100个。
     * 综上，我们分析得出，最后加的常数C只跟最高位n1是否为1有关，当最高位为1时，常数C为原数字N去掉最高位后剩下的数字+1，
     * 当最高位为1时，常数C为10bit，其中bit为N的位数-1，如N=12时，bit=1，N=232时，bit=2。
     * 于是，我们可以列出递归方程如下：
     * if(n1 == 1)
     *      f(n) = f(10bit-1) + f(n - 10bit)  + n - 10bit+ 1;
     * else
     *      f(n) = n1*f(10bit-1) + f(n – n1*10bit) + 10bit;
     * @param n
     * @return
     */
    public static long countOne(long n) {
        long count = 0;
        if (n == 0) {
            count = 0;
        } else if (n > 1 && n < 10) {
            count = 1;
        } else {
            long highest = n;// 表示最高位的数字
            int bit = 0;
            while (highest >= 10) {
                highest = highest / 10;
                bit++;
            }

            int weight = (int) Math.pow(10, bit);// 代表最高位的权重，即最高位一个1代表的大小
            if (highest == 1) {
                count = countOne(weight - 1) + countOne(n - weight) + n - weight + 1;
            } else {
                count = highest * countOne(weight - 1) + countOne(n - highest * weight) + weight;
            }
        }
        return count;
    }
    
    public static long countOne2(long n) {
        long count = 0;
        long i = 1;
        long current = 0, after = 0, before = 0;
        while ((n / i) != 0) {
            current = (n / i) % 10;
            before = n / (i * 10);
            after = n - (n / i) * i;

            if (current > 1) {
                count = count + (before + 1) * i;
            } else if (current == 0) {
                count = count + before * i;
            } else if (current == 1) {
                count = count + before * i + after + 1;
            }
            i = i * 10;
        }
        return count;
    }
}
 