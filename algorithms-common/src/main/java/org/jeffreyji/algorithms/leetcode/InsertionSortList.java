package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月11日 下午2:16:43
 * @comment:Sort a linked list using insertion sort.
 */

public class InsertionSortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(34);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(51);
        ListNode sixth = new ListNode(6);

        head = new ListNode(2);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;

        ListNode res = insertionSortList(head);
        while (res != null) {
            System.out.printf("%d ", res.val);
            res = res.next;
        }
        System.out.println();
        
        head = new ListNode(2);
        second = new ListNode(3);
        third = new ListNode(1);
        head.next = second;
        second.next = third;
        res = insertionSortList2(head);
        while (res != null) {
            System.out.printf("%d ", res.val);
            res = res.next;
        }

    }

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);

        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) {
                node = node.next;
            }
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }

        return dummy.next;
    }

    public static ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head.next;
        while (cur != null) {
            ListNode tmp = head;
            //find the first node whose value greater than current value
            while (tmp.val < cur.val && tmp != cur) {
                tmp = tmp.next;
            }
            while (tmp != cur) {
                int tmpvalue = tmp.val;
                tmp.val = cur.val;
                cur.val = tmpvalue;
                tmp = tmp.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
