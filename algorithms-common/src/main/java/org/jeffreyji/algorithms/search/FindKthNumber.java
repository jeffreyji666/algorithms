package org.jeffreyji.algorithms.search;

import java.util.Arrays;

public class FindKthNumber {
    public static int findKthLargest(int[] nums, int k) {
        if (k < 1 || nums == null) {
            return 0;
        }

        return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
    }

    public static int getKth(int k, int[] nums, int start, int end) {

        int pivot = nums[end];

        int left = start;
        int right = end;

        while (true) {

            while (nums[left] < pivot && left < right) {
                left++;
            }

            while (nums[right] >= pivot && right > left) {
                right--;
            }

            if (left == right) {
                break;
            }

            swap(nums, left, right);
        }

        swap(nums, left, end);

        if (k == left + 1) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

    public static void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }

    public static void main(String[] args) {

        int[] y = {10, 5, 3, 2, 4, 1, 6, 0, -1, 8};
        for (int i = 1; i <= y.length; i++) {
            System.out.println(findKthLargest2(y, y.length - i + 1));
        }

//
//        int j = partition(y, 0, y.length - 1, 3);
//        System.out.println(y[j]);
    }


    private static int partition(int[] a, int left, int right) {
        int pivot = a[left];
        while (left < right) {
            while (left < right && a[right] >= pivot) {
                --right;
            }
            a[left] = a[right];

            while (left < right && a[left] <= pivot) {
                ++left;
            }
            a[right] = a[left];
        }
        a[left] = pivot;

        return left;
    }

    public static int findKthLargest2(int[] nums, int k) {
        k--;
        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        while (left < right) {
            index = partition(nums, left, right);
            if (k < index) right = index - 1;
            else if (k > index) left = index + 1;
            else return nums[index];
        }
        return nums[left];
    }

}
