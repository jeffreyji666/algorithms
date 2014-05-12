package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月12日 下午6:04:23
 * @comment:Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {
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

        ListNode res = mergeTwoLists(head, head2);
        while (res != null) {
            System.out.printf("%d,", res.val);
            res = res.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l2 == null && l1 != null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }

        return dummy.next;
    }
}
