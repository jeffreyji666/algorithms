package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月17日 下午3:25:18
 * @comment:Given inorder and postorder traversal of a tree, construct the binary tree.
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {

    }

    private static int findPosition(int[] arr, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private static TreeNode myBuildTree(int[] inorder, int instart, int inend, int[] postorder, int poststart,
            int postend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);
        int position = findPosition(inorder, instart, inend, postorder[postend]);

        root.left = myBuildTree(inorder, instart, position - 1, postorder, poststart, poststart + position - instart
                - 1);
        root.right = myBuildTree(inorder, position + 1, inend, postorder, poststart + position - instart, postend - 1);
        return root;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
}
