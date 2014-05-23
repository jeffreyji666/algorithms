package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月23日 上午10:50:55 
 * @comment: Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 */

public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = { 1, 2, 1 };
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return -1;
        }
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int i = 0;
        int j = height.length - 1;
        int maxArea = -1;
        while (i < j) {
            int area = getArea(height, i, j);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    private static int getArea(int[] height, int i, int j) {
        return Math.min(height[i], height[j]) * (j - i);
    }
}
 