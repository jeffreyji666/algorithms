package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.jeffreyji.algorithms.basic.BinaryTree.TreeNode;

/**
 * @author: wgji
 * @date：2014年5月22日 下午3:47:59
 * @comment:Given a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (ie, from left to right, then right to left for the next level and alternate between).
 * For example: Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
   return its zigzag level order traversal as:
    [
      [3],
      [20,9],
      [15,7]
    ]
 */

public class BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.right = right;
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> res =  zigzagLevelOrder( root);
        for (ArrayList<Integer> level : res) {
            for (Integer val : level) {
                System.out.printf("%d,", val);
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int zigzag = 1;

        while (!q.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (zigzag % 2 == 0) {
                    level.add(0, cur.val);
                } else {
                    level.add(cur.val);
                }

                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(level);
            zigzag++;
        }
        return res;
    }
}
