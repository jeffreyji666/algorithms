package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * @author: wgji
 * @date：2014年4月30日 下午3:18:09
 * @comment:有面值为1元、3元和5元的硬币若干枚，如何用最少的硬币凑够11元?
 * (表面上这道题可以用贪心算法，但贪心算法无法保证可以求出解，比如1元换成2元的时候)
 */
public class ExchangeCoin {
    public static void main(String[] args) {
        int[] values = { 1, 3, 5 };
        makeChange(values, 11, new int[12]);
    }

    public static void makeChange(int[] values, int money, int[] coinUsed) {
        coinUsed[0] = 0;
        for (int i = 1; i <= money; i++) {
            int min = i;
            for (int j = 0; j < values.length; j++) {
                if (values[j] <= i && coinUsed[i - values[j]] + 1 < min) {
                    min = coinUsed[i - values[j]] + 1;
                }
            }
            coinUsed[i] = min;
            System.out.println(i + ":" + coinUsed[i]);
        }
    }
}
