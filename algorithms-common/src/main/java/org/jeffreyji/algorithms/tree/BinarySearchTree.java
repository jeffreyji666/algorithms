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
        // root.right = new TreeNode(3);

        System.out.println(isBinarySearchTree2(root,Integer.MIN_VALUE,Integer.MAX_VALUE));

        int[] a = { 1, 3, 2 };
        System.out.println(checkWhetherBSTFromPostOrderTraversal(a, 0, a.length - 1));
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

    public static boolean checkWhetherBSTFromPostOrderTraversal(int data[], int low, int high) {
        if (low >= high) {
            return true;
        }
        int split = -1;
        boolean found = false;
        // to see if the data can be splited as ABC where c is the last one, all members in A < c, B > c
        for (int i = low; i < high; i++) {
            if (data[i] > data[high]) {
                if (split == -1) {
                    split = i;
                    found = true;
                }
            }
            if (data[i] < data[high] && split != -1) {
                return false;
            }
        }
        // only A < c or B > c;
        if (!found) {
            return checkWhetherBSTFromPostOrderTraversal(data, low, high - 1);
        } else { // recursive way
            return checkWhetherBSTFromPostOrderTraversal(data, low, split - 1)
                    && checkWhetherBSTFromPostOrderTraversal(data, split, high - 1);
        }
    }
}
