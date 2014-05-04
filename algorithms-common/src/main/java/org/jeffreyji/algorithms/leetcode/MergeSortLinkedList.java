package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月2日 下午2:23:56
 * @comment:Sort a linked list in O(n log n) time using constant space complexity.
 */
public class MergeSortLinkedList {
    public static void main(String[] args) {

    }

    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);

        ListNode right = mergeSort(mid.next);
        mid.next = null;
        ListNode left = mergeSort(head);

        return merge(left, right);
    }

    /**
     * find the middle
     * 
     * @param head
     * @return
     */
    private static ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }

        return dummy.next;
    }
}
