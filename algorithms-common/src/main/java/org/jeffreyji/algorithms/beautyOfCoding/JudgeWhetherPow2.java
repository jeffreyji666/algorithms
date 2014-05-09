package org.jeffreyji.algorithms.beautyOfCoding;

/**
 * @author: wgji
 * @date：2014年5月10日 上午1:26:21
 * @comment: 如何判定一个数是否为2的N次方
 */

public class JudgeWhetherPow2 {
    public static boolean check1(int num) {
        int i = 1;
        while (true) {
            if (i > num)
                return false;
            if (i == num)
                return true;
            i = i * 2;
        }
    }

    public static boolean check2(int num) {
        if (num == 1)
            return true;
        else {
            do {
                if (num % 2 == 0)
                    num = num / 2;
                else
                    return false;
            } while (num != 1);
            return true;
        }
    }

    public static boolean check3(int num) {
        if (num <= 1) {
            return false;
        } else {
            return ((num & (num - 1)) == 0) ? true : false;
        }
    }

    public static void main(String[] args) {
        System.out.println(check1(32));
        System.out.println(check2(32));
        System.out.println(check3(32));
    }

}
