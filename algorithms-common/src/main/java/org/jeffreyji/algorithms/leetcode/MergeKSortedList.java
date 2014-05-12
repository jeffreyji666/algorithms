package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author: wgji
 * @date：2014年5月12日 下午4:53:00
 * @comment:
 */

public class MergeKSortedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(34);
        ListNode fourth = new ListNode(44);
        ListNode fifth = new ListNode(51);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = new ListNode(62);

        ListNode head2 = new ListNode(10);
        ListNode second2 = new ListNode(13);
        ListNode third2 = new ListNode(14);
        ListNode fourth2 = new ListNode(24);
        ListNode fifth2 = new ListNode(100);
        head2.next = second2;
        second2.next = third2;
        third2.next = fourth2;
        fourth2.next = fifth2;
        fifth2.next = new ListNode(162);

        List<ListNode> params = new ArrayList<ListNode>();
        params.add(head);
        params.add(head2);
        ListNode res = mergeKLists2(params);
        while (res != null) {
            System.out.printf("%d,", res.val);
            res = res.next;
        }
        System.out.println();
    }

    /**
     * 分治化解决，类似归并排序
     * 
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        return helper(lists, 0, lists.size() - 1);
    }

    private static ListNode helper(List<ListNode> lists, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            return merge(helper(lists, l, m), helper(lists, m + 1, r));
        }
        return lists.get(l);
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                ListNode next = l2.next;
                cur.next = l2;
                l2.next = l1;
                l2 = next;
            }
            cur = cur.next;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    /**
     * 堆解决
     * 
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(List<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }

        // PriorityQueue is a sorted queue
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            public int compare(ListNode a, ListNode b) {
                if (a.val > b.val) {
                    return 1;
                } else if (a.val == b.val) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        // add first node of each list to the queue
        for (ListNode list : lists) {
            if (list != null) {
                q.add(list);
            }
        }

        ListNode head = new ListNode(0);
        ListNode prev = head;
        while (q.size() > 0) {
            ListNode temp = q.poll();
            prev.next = temp;
            // keep adding next element of each list
            if (temp.next != null) {
                q.add(temp.next);
            }
            prev = prev.next;
        }
        return head.next;
    }
}
