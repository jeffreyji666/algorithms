package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
      [15,7],
      [9,20],
      [3]
    ]
 */

public class BinaryTreeLevelOrderTraversal2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.right = right;
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> res = levelOrderBottom(root);
        for (ArrayList<Integer> level : res) {
            for (Integer val : level) {
                System.out.printf("%d,", val);
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return res;
        }

        Stack<ArrayList<Integer>> stack = new Stack<ArrayList<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            stack.push(level);
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
    
    public static ArrayList<ArrayList<Integer>> levelOrderBottom2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int currLevelNodeNum = 1;
        int nextLevelNodeNum = 0;

        while (currLevelNodeNum != 0) {
            ArrayList<Integer> currLevelResult = new ArrayList<Integer>();
            nextLevelNodeNum = 0;

            while (currLevelNodeNum != 0) {
                TreeNode node = queue.poll();

                currLevelNodeNum--;
                currLevelResult.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                    nextLevelNodeNum++;
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    nextLevelNodeNum++;
                }
            }

            result.add(0, currLevelResult);
            currLevelNodeNum = nextLevelNodeNum;
        }
        return result;
    }
}
