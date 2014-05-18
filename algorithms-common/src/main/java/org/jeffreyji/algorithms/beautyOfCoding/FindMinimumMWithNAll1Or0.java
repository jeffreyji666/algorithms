package org.jeffreyji.algorithms.beautyOfCoding;

import java.util.LinkedList;

/**
 * @author: wgji
 * @date：2014年5月18日 下午1:58:50
 * @comment:任意给定一个正整数N，求一个最小的正整数M(M>1)，使得N*M的十进制表示形式里只含有1和0.
 * 解决这个问题首先考虑对于任意的N，是否这样的M一定存在。可以证明，M是一定存在的，而且不唯一。
 * 简单证明：列举如下数列
 * 1,10,100,1000,……
 * 每个元素modN取值只能是 0——N-1之间的整数，故至少存在N个数，他们modN的结果是相同的（抽屉原则）。
 * 从而这N个数的和必然能被N整除。
 */

public class FindMinimumMWithNAll1Or0 {
    /**
     * 编程之美 找符合条件的整数 用字符串来表示大整数避免溢出 
     * 题目：任意给定一个正整数N，求一个最小的正整数M(M>1)，使得N*M的十进制表示形式里只含有1和0
     * 
     * 假设当前正在搜索由0，1组成的K位十进制数，这样的K位十进制数共有2^k个。
     * 假设其中有两个数X、Y，它们模N同余，那么在搜索由0、1组成的K+1位十进制数时， X和Y会被扩展出四个数：10X, 10X+1, 10Y,
     * 10Y+1。 因为X和Y同余（同余完全可以看作相等），所以10X与10Y同余，10X+1与10Y+1同余。 
     * 也就是说由Y扩展出来的子树和由X扩展产生出来的子树产生完全相同的余数，
     * 如果X比Y小，那么Y肯定不是满足要求的最小的数，所以Y这棵子树可以被剪掉
     */
    public static void main(String[] args) {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            System.out.println("(" + find3(i) + " mod " + i + ")=0");
        }
        // 测试发现，在i=36时，方法一和方法二已经出错了
        for (int i = 1; i <= 36; i++) {
            System.out.println(i + "-------------------");
            System.out.println(find3(i));
            System.out.println(find2(i));
            System.out.println(find(i));
        }
    }

    /*
     * 方法三：对方法二的改进 用字符串来表示大整数，避免溢出 难点在于，
     * 如何由X求得(10*X)以及（10*X+1）对n的余数： if X%n=q, X=n*K+q then
     * (10*X)%n=(10*n*K+10*q)%n=(10*q)%n
     */
    public static String find3(int n) {
        if (n <= 0) {
            return null;
        }
        if (n == 1) {
            return "1";
        }
        String[] data = new String[n]; // data[i]代表（x%n=i）的x,x用字符串表示："1101" --> int x=1101
        data[1] = "1";
        int k = 2;
        while (true) { // 不必担心这是个死循环，可以证明，M是一定存在的
            for (int i = 0; i < n; i++) {
                String di = data[i];
                if (di == null) {
                    continue;
                }
                int len = di.length();
                if ((len + 1) == k) { // K-->K+1
                    String s = di + "0"; // di*10
                    String t = di + "1"; // di*10+1
                    int rs = (i * 10) % n; // (di*10)%n=(i*10)%n
                    int rt = (i * 10 + 1) % n; // (di*10+1)%n=(i*10+1)%n
                    if (rs == 0) {
                        return s;
                    } else if (data[rs] == null || greaterThan(data[rs], s)) { // 只保留最小的data[i]
                        data[rs] = s;
                    }
                    if (rt == 0) {
                        return t;
                    } else if (data[rt] == null || greaterThan(data[rt], t)) {
                        data[rt] = t;
                    }
                }
            }
            k++;
        }

    }

    /*
     * 比较由s和t代表的数字的大小。按位比较，从高位到低位。 如果s比t大，返回true,否则返回false
     */
    public static boolean greaterThan(String s, String t) {
        if (!s.matches("[0-9]+") || !t.matches("[0-9]+")) {
            return false;
        }
        if (s.length() != t.length()) {
            return s.length() > t.length();
        }
        int len = s.length();
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        for (int i = 0; i < len; i++) {
            if (ss[i] != tt[i]) {
                return ss[i] > tt[i];
            }
        }
        return false;
    }

    // 方法一：因为N*M的取值就是1,10,11,100,101,110,111,......所以直接在这个空间搜索
    public static int find(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int t = queue.pollFirst(); // Retrieves and removes
            if (t % n == 0) {
                return t;
            }
            queue.addLast(t * 10);
            queue.addLast(t * 10 + 1);
        }
        return -1;
    }

    // 方法二：将方法一的搜索空间按模N余数分类，但没有解决N*M超出Integer.MAX_VALUE的溢出问题
    public static int find2(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        int[] data = new int[n];
        data[1] = 1;
        int k = 2;
        while (true) {
            for (int i = 0; i < n; i++) {
                int di = data[i];
                if (di == 0) {
                    continue;
                }
                int len = 0;
                int dii = di;
                while (dii != 0) { // 计算是几位整数。计算K+1位时只需考虑K位
                    dii /= 10;
                    len++;
                }
                if ((len + 1) == k) {
                    int s = di * 10;
                    int t = di * 10 + 1;
                    if (s % n == 0) {
                        return s;
                    } else if (data[s % n] == 0 || data[s % n] > s) {
                        data[s % n] = s;
                    }
                    if (t % n == 0) {
                        return t;
                    } else if (data[t % n] == 0 || data[t % n] > t) {
                        data[t % n] = t;
                    }
                }
            }
            k++;
        }
    }
}
