package org.jeffreyji.algorithms.beautyOfCoding;

import java.util.Arrays;

/**
 * @author: wgji
 * @date：2014年5月9日 下午11:03:09
 * @comment:
 * 1. Tango是微软亚洲研究院的一个试验项目。研究院的员工和实习生们都很喜欢在Tango上面交流灌水。
 * 传说，Tango有一大“水王”，他不但喜欢发贴，还会回复其他ID发的每个帖子。
 * 坊间风闻该“水王”发帖数目超过了帖子总数的一半。
 * 如果你有一个当 前论坛上所有帖子（包括回帖）的列表，其中帖子作者的ID也在表中，
 * 你能快速找出这个传说中的Tango水王吗
 * 
 * 可抽象为: 给你一个数组，里面有超过一半的数字是一样的，你的任务就是找出这个数字。
 * 
 * 2. 随着Tango的发展，管理员发现，“超级水王”没有了。
 * 统计结果表明，有3个发帖很多的ID，他们的发帖数目都超过了帖子总数目N的1/4。
 * 你能从发帖ID列表中快速找出他们的ID吗？
 */

public class FindFloodKing {
    public static void main(String[] args) {
        int arr[] = { 9, 11, 11, 13, 11, 11, 11, 18, 19, 11, 11, 20, 11 };
        System.out.println(findFloodKing(arr));

        int[] num = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4 };
        System.out.println(Arrays.toString(findThirdFloodKing(num)));
    }

    //这个数出现的次数比剩下的数的出现次数总和还要多
    //如果下一个数字与前一个数字相同，就将出现次数加1，不同就将出现次数减1。
    //改成这样时，不管事先是否知道11是出现次数最多的，统计到最后时，都会发现留有次数的是11。
    public static int findFloodKing(int num[]) {
        int candidate = 0;
        int nTimes = 0;
        for (int i = 0; i < num.length; i++) {
            if (nTimes == 0) {
                candidate = num[i];
                nTimes = 1;
            } else {
                if (candidate == num[i]) {
                    nTimes++;
                } else {
                    nTimes--;
                }
            }
        }
        return candidate;
    }
    
    /**
     * 如果每次删除四个不同的ID（不管是否包含发帖数目超过总数1/4的ID），
     * 那么，在剩下的ID列表中，原先发帖比例大于1/4的ID所占比例仍然大于1/4。
     * 可以通过不断重复这个过程，把ID列表中的ID总数降低（转化为更小的问题），从而得到问题的答案。
     * @param num
     * @return
     */
    public static int[] findThirdFloodKing(int num[]) {
        int[] nTimes = new int[3];
        int[] candidate = new int[3];
        for (int i = 0; i < num.length; i++) {
            if (num[i] == candidate[0]) {
                nTimes[0]++;
            } else if (num[i] == candidate[1]) {
                nTimes[1]++;
            } else if (num[i] == candidate[2]) {
                nTimes[2]++;
            } else if (nTimes[0] == 0) {
                nTimes[0] = 1;
                candidate[0] = num[i];
            } else if (nTimes[1] == 0) {
                nTimes[1] = 1;
                candidate[1] = num[i];
            } else if (nTimes[2] == 0) {
                nTimes[2] = 1;
                candidate[2] = num[i];
            } else {
                nTimes[0]--;
                nTimes[1]--;
                nTimes[2]--;
            }
        }
        return candidate;
    }
}
