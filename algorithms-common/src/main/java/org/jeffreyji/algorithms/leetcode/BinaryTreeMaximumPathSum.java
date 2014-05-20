package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月15日 上午10:29:54
 * @comment:Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * For example: Given the below binary tree,
       1
      / \
     2   3
   Return 6.
 */

public class BinaryTreeMaximumPathSum {
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);

        System.out.println(maxPathSum(root));
    }

    private static int max;

    public static int maxPathSum(TreeNode root) {
        max = (root == null) ? 0 : root.val;
        findMax(root);
        return max;
    }

    public static int findMax(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // recursively get sum of left and right path
        int left = Math.max(findMax(node.left), 0);
        int right = Math.max(findMax(node.right), 0);

        // update maximum here
        max = Math.max(node.val + left + right, max);

        // return sum of largest path of current node
        return node.val + Math.max(left, right);
    }

}
