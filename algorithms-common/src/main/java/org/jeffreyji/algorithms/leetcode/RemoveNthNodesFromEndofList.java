package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月23日 下午2:47:39
 * @comment:Given a linked list, remove the nth node from the end of list and return its head.
 * For example,Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:Given n will always be valid.
 * Try to do this in one pass.
 */

public class RemoveNthNodesFromEndofList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode root = removeNthFromEnd(head,1);
        while(root != null){
            System.out.printf("%d,",root.val);
            root = root.next;
        }
        System.out.println();
        
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        root = removeNthFromEnd(head,2);
        while(root != null){
            System.out.printf("%d,",root.val);
            root = root.next;
        }
        System.out.println();
        
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        root = removeNthFromEnd(head,3);
        while(root != null){
            System.out.printf("%d,",root.val);
            root = root.next;
        }
        System.out.println();
        
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        root = removeNthFromEnd(head,4);
        while(root != null){
            System.out.printf("%d,",root.val);
            root = root.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (n-- > 0) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
        }
        // edge case
        if (fast == null) {
            return null;
        }
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }
}
