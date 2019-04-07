package org.jeffreyji.algorithms.string;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月21日 下午3:09:43
 * @comment:
 */

public class KMP {

    public static void main(String[] args) {
        kmp("121421413213211abababca", "abababca");
    }

    public static void kmp(String t, String p) {
        if (t == null || t.length() == 0 || p == null || p.length() == 0) {
            return;
        }
        int next[] = getNext(p);

        int i = 0;
        int j = 0;
        while (i < t.length() && j < p.length()) {
            if (j == -1 || t.charAt(i) == p.charAt(j)) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) {
            System.out.println(i - p.length());
            System.out.println(t.substring(i - p.length(), i));
        }
    }

    public static int[] getNext(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int[] next = new int[s.length() + 1];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < s.length()) {
            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                i++;
                j++;
                next[i] = j;
                System.out.println("if i:" + i + ",next[i]:" + next[i] + ",j:" + j);
            } else {
                System.out.println("else next[j]:" + next[j] + ",j:" + j);
                j = next[j];
            }

        }
        System.out.println(Arrays.toString(next));
        return next;
    }

}
