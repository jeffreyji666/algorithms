package org.jeffreyji.algorithms.beautyOfCoding;

/** 
 * @author:  wgji
 * @date：2014年5月5日 上午10:26:25 
 * @comment: 
 */
import java.util.Arrays;

/*
 *《编程之美》的思路是：搜索+剪枝。有点像是写下棋程序：当前情况下，把所有可能的下一步都做一遍；在这每一遍操作里面，计算出如果按这一步走的话，能不能赢（得出最优结果）。
 *《编程之美》上代码有很多错误，且每个变量的含义令人费解。因此我按我的理解写了以下代码：
 */
public class CPrefixSorting {

    private int[] cakeArray;// 要排序的烙饼数组
    private int[] reversingCakeArray;// 对cakeArray的一份拷贝。在“搜索+剪枝”过程中，对reversingCakeArray进行操作而不影响原始的排序数组--“cakeArray”。
                                     // 事实上我认为是不必要的，因为每次reverse(0,i)之后都会再次执行reverse(0,i)将reversingCakeArray还原。
    private int[] swapRecord;// 记录最优解的翻转方案。例如如果swapRecord={2,4};表示第一次把0-2之间的烙饼翻转，第二次把0-4之间的烙饼翻转
    private int[] swapingRecord;// 记录每一次翻转方案。只有在最优解的时候，才复制到swapRecord
    private int MaxSwapTimes;// 翻转次数
    private int searchTimes;// 搜索次数，尝试的次数

    public static void main(String[] args) {
        int[] cakeArray = { 4, 5, 1, 3, 2 };
        CPrefixSorting cPrefixSorting = new CPrefixSorting(cakeArray);
        cPrefixSorting.search(0);
        cPrefixSorting.output();
        cPrefixSorting.verify();// 根据swapRecord记录的最优解的翻转方案，验证一下：将烙饼翻转一次，看是否是最少的操作使烙饼有序
    }

    public void search(int step) {
        searchTimes++;
        int estimate = lowerBound(reversingCakeArray);
        if (step + estimate >= MaxSwapTimes) {// more effective than "step+estimate>MaxSwapTimes"
            return;
        }
        if (isSorted(reversingCakeArray)) {
            if (step < MaxSwapTimes) {
                MaxSwapTimes = step;
                for (int i = 0; i < MaxSwapTimes; i++) {
                    swapRecord[i] = swapingRecord[i];
                }
            }
            return;
        }
        for (int i = 1, len = cakeArray.length; i < len; i++) {
            reverse(0, i);
            swapingRecord[step] = i;
            search(step + 1);
            reverse(0, i);
        }
    }

    public void reverse(int begin, int end) {
        int len = reversingCakeArray.length;
        if (begin >= 0 && begin < len && end >= 0 && end < len && begin < end) {
            for (int i = begin, j = end; i < j; i++, j--) {
                int tmp = reversingCakeArray[i];
                reversingCakeArray[i] = reversingCakeArray[j];
                reversingCakeArray[j] = tmp;
            }
        }
    }

    public int upperBound(int n) {
        return 2 * (n - 1);
    }

    public int lowerBound(int[] array) {
        if (array == null) {
            return 0;
        }
        if (array.length < 2) {
            return 0;
        }
        int swapTimes = 0;
        for (int i = 0, len = array.length; i < len - 1; i++) {
            int diff = array[i] - array[i + 1];
            if (diff == 1 || diff == -1) {
                continue;
            } else {
                swapTimes++;
            }
        }
        return swapTimes;
    }

    public boolean isSorted(int[] array) {
        if (array == null) {
            return false;
        }
        if (array.length < 2) {
            return true;
        }
        for (int i = 0, len = array.length; i < len - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public CPrefixSorting(int[] cakeArray) {
        this.cakeArray = cakeArray;
        this.reversingCakeArray = cakeArray;
        this.MaxSwapTimes = upperBound(cakeArray.length);
        this.swapRecord = new int[MaxSwapTimes];
        this.swapingRecord = new int[MaxSwapTimes];
        this.searchTimes = 0;
    }

    public void verify() {
        System.out.println("the array to be sorted is " + Arrays.toString(reversingCakeArray));
        for (int i = 0; i < MaxSwapTimes; i++) {
            reverse(0, swapRecord[i]);
            System.out.println("step " + i + ":" + Arrays.toString(reversingCakeArray));
        }
        System.out.println("the sorted array is " + Arrays.toString(reversingCakeArray));
    }

    public void output() {
        System.out.println("MaxSwapTimes=" + MaxSwapTimes);
        System.out.println("searchTimes=" + searchTimes);
        System.out.print("swapRecord=");
        for (int i = 0; i < MaxSwapTimes; i++) {
            System.out.print(swapRecord[i] + ",");
        }
        System.out.println();
        // System.out.println("swapRecord="+Arrays.toString(swapRecord));
    }

}
