package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月3日 上午11:16:58
 * @comment:
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] data = { -1, 0, 1, 2, -1, -4 };
        ArrayList<ArrayList<Integer>> res = threeSum(data);
        for (ArrayList<Integer> item : res) {
            for (int i : item) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> threeSum(int[] data) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        if (data == null || data.length < 3) {
            return res;
        }
        Arrays.sort(data);

        for (int i = 0; i < data.length - 2; i++) {
            if (i != 0 && data[i] == data[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = data.length - 1;
            while (left < right) {
                int sum = data[left] + data[i] + data[right];
                if (sum == 0) {
                    ArrayList<Integer> item = new ArrayList<Integer>();
                    item.add(data[left]);
                    item.add(data[i]);
                    item.add(data[right]);
                    res.add(item);
                    left++;
                    right--;
                    while (left < right && data[left] == data[left - 1]) {
                        left++;
                    }
                    while (left < right && data[right] == data[right + 1]) {
                        right++;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
