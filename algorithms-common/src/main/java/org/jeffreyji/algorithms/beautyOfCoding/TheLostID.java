package org.jeffreyji.algorithms.beautyOfCoding;

/** 
 * @author:  wgji
 * @date：2014年5月5日 上午10:24:58 
 * @comment: 
 */
import java.util.Arrays;

public class TheLostID {

    /*
     * 编程之美 假设一个机器仅存储一个标号为ID的记录，假设机器总量在10亿以下且ID是小于10亿的整数，假设每份数据保存两个备份，这样就有两个机器存储了同样的数据。
     * 1.假设在某个时间得到一个数据文件ID的列表，是否能快速地找出表中仅出现一次的ID？即快速找出出现故障的机器存储的数据ID。
     * 2.如果有两台机器出现故障呢？（假设存储同一份数据的两台机器不会同时出现故障，即列表中缺少的是两个不等的ID） 这里只考虑第2个问题：有两台机器出现故障，且缺少的是两个不等的ID
     * 书上的解法4通过构造方程来解答，这有个前提是，需要知道所有的ID 我认为解法三（异或）更好 以下是解法三的实现
     */
    public static void main(String[] args) {

        int[] c = { 1, 2, 3, 4, 10, 1, 2, 3, 6, 6 };// ID列表
        TheLostID lostID = new TheLostID();
        lostID.process(c);
    }

    public void process(int[] c) {
        if (c == null || c.length < 4) {// ID列表至少应该有4个ID
            return;
        }
        if (this.isValidInput(c)) {
            this.find(c);
        }
    }

    /*
     * 例如，对于数组{1,2,3,4,10,1,2,3}，所有数字异或的结果是： xor=4^10=(1110)-->最低位的1在倒数第二位（这个位置记为i位），表示4和10在i位是不相等的
     * 那么，数组里面的数字可分为两组：一组是在i位上为1，另一组为0 让这两组数字各自内部异或，则能找到只出现一次的4和10
     */
    public void find(int[] c) {
        int len = c.length;
        int xor = 0;
        for (int i = 0; i < len; i++) {
            xor ^= c[i];
        }
        int xor2 = xor & (xor - 1);// 去掉xor二进制表示里面最低位的1
        int lastDiffBit = xor ^ xor2;// x,y在这一位是不相等的，一个为0，一个为1
        int x = 0;
        int y = 0;
        for (int i = 0; i < len; i++) {
            if ((c[i] & lastDiffBit) == lastDiffBit) {
                x ^= c[i];
            } else {
                y ^= c[i];
            }
        }
        System.out.println("x,y=" + x + "," + y);
    }

    /*
     * 判断数组是否符合以下条件： 
     * 1、数组里面的数字，要么出现一次，要么出现两次 
     * 2、出现一次的数字，有且只有两个 最快的方法当然是用哈希表 这里用的是 排序+遍历统计（如果数据量大，用排序也不如哈希表好）
     */
    public boolean isValidInput(int[] c) {
        Arrays.sort(c);// 排序
        int len = c.length;
        int count = 0;
        if (c[0] != c[1]) {
            count++;
        }
        if (c[len - 1] != c[len - 2]) {
            count++;
        }
        for (int i = 0; i < len; i++) {
            if (i <= len - 3) {
                if (c[i] == c[i + 1] && c[i + 1] == c[i + 2]) {// 不能存在三个相等的数
                    return false;
                }
            }
            // 统计不相等的数有几个
            if (0 < i && i < len - 1 && c[i] != c[i - 1] && c[i] != c[i + 1]) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return count == 2;
    }
}
