package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wgji
 * @date：2014年5月16日 上午11:42:21
 * @comment:Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
   return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
 */

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.right = right;
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> res = levelOrder(root);
        for (ArrayList<Integer> level : res) {
            for (Integer val : level) {
                System.out.printf("%d,", val);
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        return result;
    }
}
