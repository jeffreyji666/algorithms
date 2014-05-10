package org.jeffreyji.algorithms.beautyOfCoding;

/**
 * @author: wgji
 * @date：2014年5月9日 上午9:57:54
 * @comment:count the 1 digit num of a binary string or a basic type.
 */

public class CountOneDigitOfBinaryString {
    public static void main(String[] args) {
        System.out.println(count(11));
        System.out.println(count2(11));
        System.out.println(count3(11));
        System.out.println(count4(11));
        System.out.println(count5(11));
        System.out.println(count6(11));
    }

    public static int count(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                count++;
            }
            num = num / 2;
        }
        return count;
    }

    public static int count6(int num) {
        int count = 0;
        while (num > 0) {
            count += num & 0x01;
            num >>= 1;
        }
        return count;
    }

    public static int count2(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    public static int count3(int num) // 假设int是32位
    {
        int count = num;
        int a = 0x55555555; // 010101010101010101010101010101 //用于相邻的两位相加
        int b = 0x33333333; // 用于相邻的四位相加
        int c = 0x0f0f0f0f;
        int d = 0x00ff00ff;
        int e = 0x0000ffff;

        count = (count & a) + ((count >> 1) & a);
        count = (count & b) + ((count >> 2) & b);
        count = (count & c) + ((count >> 4) & c);
        count = (count & d) + ((count >> 8) & d);
        count = (count & e) + ((count >> 16) & e);

        return count;
    }

    public static int count4(int x) {
        int n = (x >> 1) & 033333333333;
        x = x - n;
        n = (n >> 1) & 033333333333;
        x = x - n;
        x = (x + (x >> 3)) & 030707070707;
        x = x % 63;
        return x;
    }

    public static int count5(int x) {
        int v = 7;
        v = v - ((v >> 1) & 0x55555555); // reuse input as temporary
        v = (v & 0x33333333) + ((v >> 2) & 0x33333333); // temp
        int c = ((v + (v >> 4) & 0xF0F0F0F) * 0x1010101) >> 24; // count

        return c;
    }
}
