package org.jeffreyji.algorithms.toutiao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiwengang
 * @since 2019/3/27 下午11:52
 * 双向链表排序，时间复杂度不超过o(nlogn) ,最坏情况不能达到 n^2,空间复杂度不能超过 o(1)
 */
public class SortDoubleLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(6).add(16).add(26).add(14).add(9).add(34).add(23);

        sort(list);
    }

    static void sort(LinkedList list) {
        System.out.println(list);
        dealLinkedListToBintree(list);
        list = preorderBintree(list);
        System.out.println(list);
    }

    static LinkedList preorderBintree(LinkedList list) {
        LinkedList res = new LinkedList();
        Node root = list.head;
        Node nodeTemp = root;
        List<Node> stack = new ArrayList();
        while (nodeTemp != null || stack.size() > 0) {
            while (nodeTemp != null) {
                stack.add(nodeTemp);
                nodeTemp = nodeTemp.prev;
            }
            nodeTemp = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            res.add(nodeTemp.value);

            nodeTemp = nodeTemp.next;
        }
        return res;
    }

    static void dealLinkedListToBintree(LinkedList list) {
        Node root = list.head;
        Node nodeNext = root.next;
        Node nodeInsert = null, temp = null;
        root.next = null;
        while (nodeNext != null) {
            nodeInsert = root;
            temp = nodeNext;
            nodeNext = nodeNext.next;
            while (true) {
                if (temp.value < nodeInsert.value) {
                    if (nodeInsert.prev == null) {
                        nodeInsert.prev = temp;
                        temp.prev = null;
                        temp.next = null;
                        break;
                    }
                    nodeInsert = nodeInsert.prev;
                } else {
                    if (nodeInsert.next == null) {
                        nodeInsert.next = temp;
                        temp.prev = null;
                        temp.next = null;
                        break;
                    }
                    nodeInsert = nodeInsert.next;
                }
            }
        }

    }
}

class Node {

    int value;
    Node prev;
    Node next;

    public Node(int x) {
        value = x;
    }

    public Node(int x, Node p, Node n) {
        value = x;
        prev = p;
        next = n;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}

class LinkedList {

    Node head = null;
    int size = 0;

    public LinkedList add(int x) {
        if (head == null) {
            head = new Node(x, null, null);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(x, temp, null);
        }
        size++;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(size).append("[");
        Node temp = head;
        while (temp != null) {
            builder.append(temp.value);
            temp = temp.next;
            if (temp != null) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
