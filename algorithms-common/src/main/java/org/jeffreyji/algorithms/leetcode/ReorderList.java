package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月8日 下午4:22:02
 * @comment:Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→… 
 * You must do this in-place without altering the nodes' values. 
 * For example,Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

public class ReorderList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;

        ListNode res = reorderList(head);
        while(res !=null){
            System.out.printf("%d ",res.val);
            res = res.next;
        }
    }

    public static ListNode reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;

        return merge(head, tail);
    }

    private static ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    private static ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode merge(ListNode head1, ListNode head2) {
        int index = 0;
        ListNode dummy = new ListNode(0);
        ListNode headNode = dummy;
        while (head1 != null && head2 != null) {
            if (index % 2 == 0) {
                headNode.next = new ListNode(head1.val);
                head1 = head1.next;
            } else {
                headNode.next = new ListNode(head2.val);
                head2 = head2.next;
            }
            headNode = headNode.next;
            index++;
        }
        if (head1 != null) {
            headNode.next = head1;
        } else {
            headNode.next = head2;
        }
        
        return dummy.next;
    }
}
