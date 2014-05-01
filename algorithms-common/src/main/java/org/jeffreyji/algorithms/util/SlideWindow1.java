package org.jeffreyji.algorithms.util;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月1日 下午2:38:02
 * @comment:
 */

public class SlideWindow1 {
    public static void main(String[] args) {
        int[] x = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.binarySearch(x, 8));
        
//        int[] h = { 5, 8, 4, 4, 8, 4 };
//        int[] mq = new int[h.length]; // 单调队列，对内元素为建筑物高度的下标
//        int[] left = new int[h.length]; // left[i]：在第i个建筑物左侧，不比它的高度小的建筑物数量
//        int[] right = new int[h.length]; // right[i]：在第i个建筑物右侧，不比它的高度小的建筑物数量
//
//        calcLeft(h, mq, left);
//        calcRight(h, mq, right);
//        System.out.println(maxRectArea(h, left, right));
    }

    public static int MaxRectArea2(int[] h) {
        int maxArea = 0;
        int i, j;
        for (i = 0; i < h.length; i++) {
            int count = 1;
            // 统计在当前建筑物i的左边，与h[i]相同或更高的建筑物有多少
            for (j = i - 1; j >= 0 && h[j] >= h[i]; j--) {
                count++;
            }
            // 右边同理
            for (j = i + 1; j < h.length && h[j] >= h[i]; j++) {
                count++;
            }
            int area = count * h[i];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void calcLeft(int[] h, int[] mq, int[] left) {
        mq[0] = 0;
        int front = 0, rear = 1;
        for (int i = 1; i < h.length; i++) {
            while (front < rear && h[i] <= h[mq[rear - 1]]) {
                rear--;
            }
            left[i] = i - mq[rear - 1] - 1;
            mq[rear++] = i;
        }
    }

    public static void calcRight(int[] h, int[] mq, int[] right) {
        mq[0] = h.length + 1;
        int front = 0, rear = 1;

        int i;
        for (i = h.length - 1; i >= 1; i--) {
            while (front < rear && h[i] <= h[mq[rear - 1]]) {
                rear--;
            }
            right[i] = mq[rear - 1] - i - 1;
            mq[rear++] = i;
        }
    }

    public static int maxRectArea(int[] h, int[] left, int[] right) {
        int maxArea = -1;
        int i;
        for (i = 1; i < h.length; i++) {
            int area = (left[i] + right[i] + 1) * h[i];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}
