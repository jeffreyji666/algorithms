package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月14日 下午10:57:12 
 * @comment: Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,return 1->2->2->4->3->5.
 */

public class PartitionList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(4);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(2);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(2);

        ListNode res = partition2(root, 3);
        while (res != null) {
            System.out.printf("%d,", res.val);
            res = res.next;
        }
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode fakeHead1 = new ListNode(0);
        ListNode fakeHead2 = new ListNode(0);
        fakeHead1.next = head;

        ListNode original = head;
        ListNode smallerThanX = fakeHead1;
        ListNode greaterThanX = fakeHead2;
        while (original != null) {
            if (original.val < x) {
                smallerThanX = smallerThanX.next;
            } else {
                greaterThanX.next = original;
                smallerThanX.next = original.next;
                greaterThanX = greaterThanX.next;
            }
            original = original.next;
        }
        // close the list
        greaterThanX.next = null;
        smallerThanX.next = fakeHead2.next;

        return fakeHead1.next;
    }
    
    public static ListNode partition2(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode smallerThanX = dummy1;
        ListNode greaterThanX = dummy2;
        ListNode p = head;
        while (p != null) {
            ListNode node = new ListNode(p.val);
            if (p.val < x) {
                smallerThanX.next = node;
                smallerThanX = smallerThanX.next;
            } else {
                greaterThanX.next = node;
                greaterThanX = greaterThanX.next;
            }
            p = p.next;
        }
        smallerThanX.next = dummy2.next;
        return dummy1.next;
    }

}
 