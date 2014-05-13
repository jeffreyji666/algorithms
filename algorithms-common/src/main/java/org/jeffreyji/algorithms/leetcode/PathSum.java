package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月13日 上午9:45:47
 * @comment:Given a binary tree and a sum, 
 * determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * For example:Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
   return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

public class PathSum {
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

        System.out.println(hasPathSum(root, 22));
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return dfs(root, 0, sum);
    }

    public static boolean dfs(TreeNode root, int num, int sum) {
        if (root == null) {
            return false;
        }
        num += root.val;
        if (root.left == null && root.right == null) {
            if (num == sum) {
                return true;
            }
            return false;
        }
        return dfs(root.left, num, sum) || dfs(root.right, num, sum);
    }
}
