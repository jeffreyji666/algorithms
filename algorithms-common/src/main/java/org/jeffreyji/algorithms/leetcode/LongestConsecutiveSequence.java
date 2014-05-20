package org.jeffreyji.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wgji
 * @date：2014年5月20日 下午2:17:23
 * @comment:Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example, Given [100, 4, 200, 1, 3, 2], The longest consecutive elements sequence is [1, 2, 3, 4]. 
 * Return its length: 4. Your algorithm should run in O(n) complexity.
 */

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] num = { 100, 4, 200, 1, 3, 2, 5 };
        System.out.println(longestConsecutive(num));
    }

    // Sort & search: space O(1), time O(n logn)
    // HashMap: space O(n), time O(n)
    public static int longestConsecutive(int[] num) {
        Map<Integer, Integer> hs = new HashMap<Integer, Integer>();
        for (int i : num) {
            hs.put(i, 0);
        }
        int maxl = 1;
        for (int i : num) {
            if (hs.get(i) == 1) {
                continue;
            }

            int tmp = i;
            int currentMax = 1;
            while (hs.containsKey(tmp + 1)) {
                currentMax++;
                tmp++;
                hs.put(tmp, 1);
            }

            tmp = i;
            while (hs.containsKey(tmp - 1)) {
                currentMax++;
                tmp--;
                hs.put(tmp, 1);
            }

            maxl = Math.max(currentMax, maxl);
        }

        return maxl;
    }
}
