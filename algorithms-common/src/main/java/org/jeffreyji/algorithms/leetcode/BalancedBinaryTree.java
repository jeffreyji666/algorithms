package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月23日 下午5:51:18 
 * @comment: Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */

public class BalancedBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        TreeNode left2 = new TreeNode(3);
        TreeNode right2 = new TreeNode(3);
        left.left = left2;
        right.right = right2;
        left2.left = new TreeNode(4);
        right2.right = new TreeNode(4);
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }
}
 