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
        int[] data = { -2, 3, 5, -1, -2, -3, 5, 6, 29, 4, 7, 8 };
        ArrayList<ArrayList<Integer>> res = threeSum(data);
        for (ArrayList<Integer> item : res) {
            for (int i : item) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {

        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length < 3) {
            return rst;
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i != 0 && num[i] == num[i - 1]) {
                continue; // to skip duplicate numbers; e.g [0,0,0,0]
            }

            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[left] + num[right] + num[i];
                if (sum == 0) {
                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(num[i]);
                    tmp.add(num[left]);
                    tmp.add(num[right]);
                    rst.add(tmp);
                    left++;
                    right--;
                    while (left < right && num[left] == num[left - 1]) { // to skip duplicates
                        left++;
                    }
                    while (left < right && num[right] == num[right + 1]) { // to skip duplicates
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return rst;
    }
}
