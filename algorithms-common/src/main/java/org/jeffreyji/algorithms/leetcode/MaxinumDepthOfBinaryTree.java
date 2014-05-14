package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月14日 下午5:33:45
 * @comment:Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */

public class MaxinumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(8);
        TreeNode left2 = new TreeNode(11);
        TreeNode left3 = new TreeNode(13);
        TreeNode right2 = new TreeNode(4);

        root.left = left1;
        root.right = right1;
        left1.left = left2;
        right1.left = left3;
        right1.right = right2;
        left2.left = new TreeNode(7);
        left2.right = new TreeNode(2);
        right2.right = new TreeNode(1);

        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0);
    }

    private static int dfs(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(dfs(root.left, depth), dfs(root.right, depth)) + 1;
    }
}
