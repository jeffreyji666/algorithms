package org.jeffreyji.algorithms.leetcode; 

/** 
 * @author:  wgji
 * @date：2014年5月15日 下午3:30:37 
 * @comment:Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:

 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


 * OJ's Binary Tree Serialization:
 * The serialization of a binary tree follows a level order traversal, 
 * where '#' signifies a path terminator where no node exists below.

 * Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}". 
 */

public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBinarySearchTree(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }

        return isBinarySearchTree(root.left, min, root.val) && isBinarySearchTree(root.right, root.val, max);
    }
}
 