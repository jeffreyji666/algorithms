package org.jeffreyji.algorithms.tree;

/**
 * @author: wgji
 * @date：2014年5月15日 下午3:04:19
 * @comment:
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        // root.right = new TreeNode(3);

        System.out.println(isValidBST(root));
    }

    private static int previous = Integer.MIN_VALUE;

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // the left sub tree
        if (!isValidBST(root.left)) {
            return false;
        }
        // the current node
        if (root.val <= previous) {
            return false;
        }
        previous = root.val;

        // the right subtree
        if (!isValidBST(root.right)) {
            return false;
        }

        return true;
    }

    /*
     * 给定的二叉树是BST则返回true，且它的值 >min 以及 < max.
     */
    public static boolean isBinarySearchTree2(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        // 如果不满足min和max约束，返回false
        if (root.val <= min || root.val >= max) {
            return false;
        }
        // 递归判断左右子树是否满足min和max约束条件
        return isBinarySearchTree2(root.left, min, root.val) && isBinarySearchTree2(root.right, root.val, max);
    }
}
