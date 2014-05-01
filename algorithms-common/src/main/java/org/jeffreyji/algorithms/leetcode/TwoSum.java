package org.jeffreyji.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wgji
 * @date：2014年5月2日 上午3:54:28
 * @comment: Given an array of integers, find two numbers such that they add up to a specific target number. The
 *           function twoSum should return indices of the two numbers such that they add up to the target, where index1
 *           must be less than index2. Please note that your returned answers (both index1 and index2) are not
 *           zero-based. You may assume that each input would have exactly one solution. Input: numbers={2, 7, 11, 15},
 *           target=9 Output: index1=1, index2=2
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] a = { 2, 7, 3, 6, 11, 15 };

        int[] res = twoSum(a, 9);
        for (int item : res) {
            System.out.println(item);
        }
        res = twoSum2(a, 9);
        for (int item : res) {
            System.out.println(item);
        }
    }

    public static int[] twoSum(int[] a, int target) {
        if (a == null || a.length < 2) {
            return null;
        }

        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i + 1);
        }
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(target - a[i])) {
                int index1 = i + 1;
                int index2 = map.get(target - a[i]);
                if (index1 == index2) {
                    continue;
                }
                res[0] = index1;
                res[1] = index2;
                return res;
            }
        }
        return res;
    }

    public static int[] twoSum2(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        Arrays.sort(numbers);
        int left = 0;
        int right = numbers.length - 1;
        int[] rst = new int[2];

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                rst[0] = left + 1;
                rst[1] = right + 1;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return rst;
    }
}
