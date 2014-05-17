package org.jeffreyji.algorithms.leetcode;

import java.util.Stack;

/**
 * @author: wgji
 * @date：2014年5月18日 上午2:36:53
 * @comment:
 */

public class FlatternBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        flatten2(root);
        while (root != null) {
            System.out.printf("%d,", root.val);
            root = root.right;
        }
    }

    public static void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;

        while (p != null || !stack.empty()) {

            if (p.right != null) {
                stack.push(p.right);
            }

            if (p.left != null) {
                p.right = p.left;
                p.left = null;
            } else if (!stack.empty()) {
                TreeNode temp = stack.pop();
                p.right = temp;
            }

            p = p.right;
        }
    }
    
    
    private static TreeNode lastNode = null;

    public static void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }
}
