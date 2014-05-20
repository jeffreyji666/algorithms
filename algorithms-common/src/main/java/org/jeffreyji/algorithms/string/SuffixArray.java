package org.jeffreyji.algorithms.string;

/** 
 * @author:  wgji
 * @date：2014年5月20日 下午4:54:38 
 * @comment: 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuffixArray {
    public static void main(String[] args) {
        String s = "abcdaabcd";
        System.out.println(maxLength(s));
    }

    public static String maxLength(String s) {
        List<String> postfix = new ArrayList<String>();// 存储后缀数组，并排序
        for (int i = 0; i < s.length(); i++) {
            postfix.add(s.substring(i));
        }
        Collections.sort(postfix);
        int temp = 0;// 最长的重复子序列截取位置
        int maxis = 0;

        for (int i = 0; i < postfix.size() - 1; i++) {// 循环，找出最长的重复子序列下标，最长的重复子序列截取位置
            int len = maxlength(postfix.get(i), postfix.get(i + 1));// 获取后缀数组两两比较的相同子序列长度
            if (len >= temp) {
                temp = len;
                maxis = i;
            }
        }
        // 根据最长的重复子序列下标和截取位置，获得最长的重复子序列
        return postfix.get(maxis).substring(0, temp);
    }

    private static int maxlength(String next, String next2) {
        char[] c1 = next.toCharArray();
        char[] c2 = next2.toCharArray();
        int maxlen = 0;
        for (int i = 0; i < (c1.length > c2.length ? c2.length : c1.length); i++) {
            if (c1[i] == c2[i]) {
                maxlen++;
            } else {
                return maxlen;
            }
        }
        return maxlen;
    }
}
