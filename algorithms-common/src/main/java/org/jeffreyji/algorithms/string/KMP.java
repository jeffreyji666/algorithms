package org.jeffreyji.algorithms.string;

/** 
 * @author:  wgji
 * @date：2014年5月2日 上午12:37:39 
 * @comment: 
 */

import java.io.IOException;

public class KMP {
    public static void main(String args[]) throws IOException {
        kmp("test", "testtest");
    }

    // getNext();
    public static int[] getNext(char x[]) {
        int next[] = new int[x.length];
        next[0] = -1;

        int i = 0;
        int j = -1;
        while (i < next.length - 1) // take care
        {
            if (j == -1 || x[i] == x[j]) {
                ++i;
                ++j;
                if (x[i] == x[j])
                    next[i] = next[j];
                else
                    next[i] = j;

            } else {
                j = next[j];
            }
        }
        return next;

    }

    // KMP
    public static void kmp(String t, String p) {
        char ch[] = p.toCharArray();
        int next[] = getNext(ch);

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
        if (j == p.length())
            System.out.println(i - p.length());
        else
            System.out.println("no");
    }
}
