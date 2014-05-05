package org.jeffreyji.algorithms.util; 

/** 
 * @author:  wgji
 * @date：2014年5月5日 上午10:27:23 
 * @comment: 
 */
import java.util.LinkedList;

/*

单调队列 滑动窗口
单调队列是这样的一个队列：队列里面的元素是有序的，是递增或者递减

题目：给定一个长度为N的整数数列a(i),i=0,1,...,N-1和窗长度k.

要求：f(i) = max{a(i-k+1),a(i-k+2),..., a(i)},i = 0,1,...,N-1

问题的另一种描述就是用一个长度为k的窗在整数数列上移动，求窗里面所包含的数的最大值

详情见 
@陈利人 http://weibo.com/lirenchen
http://weibo.com/1915548291/z3T4HbRRr 
该条微博下有人回复说：
@我是RickyYang：维护一个有k个元素大顶堆，如果每次移出堆的是堆顶，那么将新数替换堆顶，并对堆做调整，
如果移出不是堆顶，则只需将新数和堆顶比较，大的留在堆顶，小的替换移出的数，无需调整堆。最坏的话时间复杂度nlogk,最好n-k+logk (11月6日 08:22)

这应该也是可行的。我的刚开始想到的也是用最大堆。不过稍嫌麻烦的是：“如果移出不是堆顶，则只需将新数和堆顶比较，大的留在堆顶，小的替换移出的数”
这样的话，要记录窗口移动后被移出窗口的那个数，同时“替换移出的数”，要在最大堆执行搜索

单调队列的参考思路：
http://xuyemin520.is-programmer.com/posts/25964

1.首先看插入元素：为了保证队列的递减性，我们在插入元素v的时候，要将队尾的元素和v比较，
如果队尾的元素不大于v,则删除队尾的元素，然后继续将新的队尾的元素与v比较，直到队尾的元素大于v,这个时候我们才将v插入到队尾

2.队尾的删除刚刚已经说了，那么队首的元素什么时候删除呢？
由于我们只需要保存i的前k-1个元素中的最大值，所以当队首的元素的索引或下标小于 i-k+1的时候，
就说明队首的元素对于求f(i)已经没有意义了，因为它已经不在窗里面了。所以当index[队首元素]<i-k+1时，将队首 元素删除

在下面的代码里面，实现队列时，我直接用了LinkedList，因为手工用数组维护一个单调队列要考虑挺多的。现在把重点放在算法的思路上^_^

*/
public class SlideWindow3 {

    public static void main(String[] args) {
        /*
        int[] array = { 8, 7, 12, 5, 16, 9, 17, 2, 4, 6 };
        int k = 3;
         */
        
        int[] array = { 3, 4, 5, 7, 3, 5, 2, 9 };
        int k = 3;
        
        int[] max = maxInWindow(array, k);
        for (int i : max) {
            System.out.println(i);
        }
    }

    public static int[] maxInWindow(int[] array, int k) {
        if (k <= 0 || array == null || array.length == 0) {
            System.out.println("invalid input");
            return new int[0];
        }
        int len = array.length;
        int[] max = new int[len];
        if (k == 1) {
            System.arraycopy(array, 0, max, 0, len);    //复制一份，不影响原数组
        } else {
            LinkedList<Item> queue = new LinkedList<Item>();
            for (int i = 0; i < len; i++) {
                Item curItem = new Item(array[i], i);
                if (queue.isEmpty()) {
                    queue.addLast(curItem);
                } else {
                    Item head = queue.getFirst();
                    int headIndex = head.getIndex();
                    
                    //如果队首元素已不在窗口内，则删除
                    if (headIndex < (i - k + 1)) {  
                        queue.removeFirst();
                    }
                    
                    //插入元素
                    while (!queue.isEmpty()) {
                        Item tail = queue.getLast();
                        int tailValue = tail.getValue();
                        if (tailValue < array[i]) {
                            queue.removeLast();
                            if (queue.isEmpty()) {
                                queue.addLast(curItem);
                                break;
                            }
                        } else if (tailValue > array[i]) {
                            queue.addLast(curItem);
                        } else {
                            break;
                        }
                    }
                }
                
                //每次操作后，队首元素为当前窗口的最大值
                if (!queue.isEmpty()) {
                    max[i] = queue.getFirst().getValue();
                }
            }
        }
        return max;
    }

}

class Item {
    
    //数组元素值
    private int value;
    
    //数组元素对应的下标
    private int index;

    public Item(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

}
