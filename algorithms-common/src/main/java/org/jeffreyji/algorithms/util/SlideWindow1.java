package org.jeffreyji.algorithms.util;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月1日 下午2:38:02
 * @comment:最近，afy决定给TOJ印刷广告，广告牌是刷在城市的建筑物上的，城市里有紧靠着的N个建筑。
 * afy决定在上面找一块尽可能大的矩形放置广告牌。我们假设每个建筑物都有一个高度，
 * 从左到右给出每个建筑物的高度H1,H2…HN，且0<Hi<=1,000,000,000，并且我们假设每个建筑物的宽度均为1。
 * 要求输出广告牌的最大面积。
 * 【输入样例】
 * 6
 * 5 8 4 4 8 4
 * 【输出样例】
 * 24
 */
public class SlideWindow1 {
    public static void main(String[] args) {
        int[] h = { -1, 5, 8, 4, 4, 8, 4, -1 };
        int[] mq = new int[h.length - 1]; // 单调队列，对内元素为建筑物高度的下标
        int[] left = new int[h.length - 1]; // left[i]：在第i个建筑物左侧，不比它的高度小的建筑物数量
        int[] right = new int[h.length - 1]; // right[i]：在第i个建筑物右侧，不比它的高度小的建筑物数量
        System.out.println(Arrays.toString(h));
        calcLeft(h, mq, left);
        calcRight(h, mq, right);
        System.out.println(maxRectArea(h, left, right));
        System.out.println(maxRectArea2(h));

    }

    public static int maxRectArea2(int[] h) {
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
        int rear = 1;
        for (int i = 1; i <= h.length - 2; i++) {
            while (rear > 0 && h[i] <= h[mq[rear - 1]]) {
                rear--;
            }
            System.out.printf("left[%d]:%d,mq[%d]:%d\n", i, i - mq[rear - 1] - 1, rear, i);
            left[i] = i - mq[rear - 1] - 1;
            mq[rear++] = i;
        }
    }

    public static void calcRight(int[] h, int[] mq, int[] right) {
        mq[0] = h.length - 1;
        int rear = 1;

        for (int i = h.length - 2; i >= 1; i--) {
            while (rear > 0 && h[i] <= h[mq[rear - 1]]) {
                rear--;
            }
            System.out.printf("right[%d]:%d,mq[%d]:%d\n", i, mq[rear - 1] - i - 1, rear, i);
            right[i] = mq[rear - 1] - i - 1;
            mq[rear++] = i;
        }
    }

    public static int maxRectArea(int[] h, int[] left, int[] right) {
        int maxArea = -1;
        for (int i = 1; i < h.length - 1; i++) {
            int area = (left[i] + right[i] + 1) * h[i];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}
