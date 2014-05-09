package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月9日 下午2:49:03
 * @comment:Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *   1
 *  / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 */

public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNumbers(root));
        System.out.println(sumNumbers2(root));
    }

    public static int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root, 0, 0);
    }

    public static int helper(TreeNode node, int num, int sum) {
        if (node == null)
            return sum;

        num = num * 10 + node.val;

        // leaf reached
        if (node.left == null && node.right == null) {
            sum += num;
            return sum;
        }

        // sum of left subtree + right subtree
        sum = helper(node.left, num, sum) + helper(node.right, num, sum);
        return sum;
    }
    
    public static int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode root, int prev) {
        if (root == null) {
            return 0;
        }

        int sum = root.val + prev * 10;
        if (root.left == null && root.right == null) {
            return sum;
        }

        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}
