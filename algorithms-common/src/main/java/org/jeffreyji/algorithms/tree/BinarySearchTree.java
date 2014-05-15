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
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(isBinarySearchTree2(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    /* 该函数判断二叉树p是否是一棵二叉搜索树，且其结点值都大于prev */
    public static boolean isBinarySearchTree(TreeNode root, int prev) {
        if (root == null) {
            return true;
        }
        if (isBinarySearchTree(root.left, prev)) { // 如果左子树是二叉搜索树，且结点值都大于prev
            // 判断当前结点值是否大于prev，因为此时prev已经设置为已经中序遍历过的结点的最大值。
            if (root.val > prev) {
                prev = root.val;
                // 若结点值大于prev，则设置prev为当前结点值，并判断右子树是否二叉搜索树且结点值都大于prev。
                return isBinarySearchTree(root.right, prev);
            } else {
                return false;
            }
        } else {
            return false;
        }
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
