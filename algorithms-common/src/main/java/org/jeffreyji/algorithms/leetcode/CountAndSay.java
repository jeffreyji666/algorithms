package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月25日 上午1:01:13
 * @comment:The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */

public class CountAndSay {

    public static void main(String[] args) {
        String res = countAndSay(50);
        System.out.println(res);
        System.out.println(res.length());
    }

    public static String countAndSay(int n) {
        assert (n > 0);
        if (n == 1) {
            return "1";
        }

        String s = "1";
        StringBuilder res = new StringBuilder();
        int cnt = 0;
        int round = 0;
        int i;
        while (++round < n) {
            cnt = 1;
            for (i = 1; i < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    cnt++;
                } else {
                    res.append(cnt).append(s.charAt(i - 1));
                    cnt = 1;
                }
            }
            res.append(cnt).append(s.charAt(i - 1));
            s = res.toString();
            res.delete(0, res.length());
        }
        return s;
    }

}
