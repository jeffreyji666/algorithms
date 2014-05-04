package org.jeffreyji.algorithms.util;

/**
 * @author: wgji
 * @date：2014年5月1日 下午3:24:58
 * @comment:
 * Description

An array of size n ≤ 106 is given to you. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves rightwards by one position. Following is an example: 
The array is [1 3 -1 -3 5 3 6 7], and k is 3.
Window position Minimum value   Maximum value
[1  3  -1] -3  5  3  6  7   -1  3
 1 [3  -1  -3] 5  3  6  7   -3  3
 1  3 [-1  -3  5] 3  6  7   -3  5
 1  3  -1 [-3  5  3] 6  7   -3  5
 1  3  -1  -3 [5  3  6] 7   3   6
 1  3  -1  -3  5 [3  6  7]  3   7
Your task is to determine the maximum and minimum values in the sliding window at each position. 

Input

The input consists of two lines. The first line contains two integers n and k which are the lengths of the array and the sliding window. There are n integers in the second line. 
Output

There are two lines in the output. The first line gives the minimum values in the window at each position, from left to right, respectively. The second line gives the maximum values. 
Sample Input

8 3
1 3 -1 -3 5 3 6 7
Sample Output

-1 -3 -3 -3 3 3
3 3 5 5 6 7

 * 移动区间(长度固定)最值问题。
【分析】
    这类思想在单调队列优化思想中尤其重要：区间长度为k，求区间内的最大值，考虑第i个数和第j个数，j-i<k，
    若a[i]<a[j]，那么a[i]将毫无用处。直觉上理解，因为窗口的移动，a[i]要比a[j]先移出去，无论如何，区间的最大值都不可能是a[i]。
    这样，考虑构造一个单调递增的队列，存放相应的序号，当a[队尾]>=要入队数据a[i]，删除队尾元素；当队头<=i-k时，删除队头元素。
 */
public class SlideWindow2 {
    public static void main(String[] args) {
        minMaxOfK();
    }

    /**
     * 单调队列： 加入找最小数，考虑顺序a,b(b在a的后面)， 
     * 若b<a,当b入队列后，a不可能称为最小值(a比b先出)，删去。 
     * 每个元素出队列和入队列一次，时间复杂度为O(n)
     */
    public static void minMaxOfK() {
        int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
        for (int aa : a) {
            System.out.printf(aa + ",");
        }
        System.out.println();
        int k = 3;
        int[] sw = new int[a.length]; // 单调递减队列(最大),单调递增队列(最小)

        // 递增
        int i;
        int head = 1;
        int tail = 0;
        for (i = 0; i < k - 1; i++) {
            while (head <= tail && a[sw[tail]] >= a[i]) {
                tail--;
            }
            tail++;
            sw[tail] = i;
            System.out.printf("----sw[%d]:%d----\n",  tail, sw[tail]);
        }
        System.out.println("***************");
        for (i = k - 1; i < a.length; i++) {
            while (head <= tail && a[sw[tail]] >= a[i]) {
                tail--;
            }
            tail++;
            sw[tail] = i;
            while (sw[head] < i - k + 1) {
                head++;
            }
            System.out.printf("----swhead[%d]:%d,swtail[%d]:%d----\n", head, sw[head], tail, sw[tail]);
            System.out.printf("%d", a[sw[head]]);
            System.out.printf("%c", i == a.length - 1 ? '\n' : ' ');
        }
        System.out.println("***************");

        // 递减
        head = 1;
        tail = 0;
        for (i = 0; i < k - 1; i++) {
            while (head <= tail && a[sw[tail]] <= a[i]) {
                tail--;
            }
            tail++;
            sw[tail] = i;
            System.out.printf("----sw[%d]:%d----\n", tail, sw[tail]);
        }
        System.out.println("***************");
        for (i = k - 1; i < a.length; i++) {
            while (head <= tail && a[sw[tail]] <= a[i]) {
                tail--;
            }
            tail++;
            sw[tail] = i;
            while (sw[head] < i - k + 1) {
                head++;
            }
            System.out.printf("----sw[%d]:%d,sw[%d]:%d----\n", head, sw[head], tail, sw[tail]);
            System.out.printf("%d", a[sw[head]]);
            System.out.printf("%c", i == a.length - 1 ? '\n' : ' ');
        }
    }
}
