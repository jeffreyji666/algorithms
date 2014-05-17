package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wgji
 * @date：2014年5月17日 下午3:25:18
 * @comment:Given inorder and postorder traversal of a tree, construct the binary tree.
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {
        List<Integer> inOrder = new ArrayList<Integer>();
        inOrder.add(8);
        inOrder.add(4);
        inOrder.add(10);
        inOrder.add(9);
        inOrder.add(11);
        inOrder.add(2);
        inOrder.add(5);
        inOrder.add(1);
        inOrder.add(6);
        inOrder.add(3);
        inOrder.add(7);

        List<Integer> postOrder = new ArrayList<Integer>();
        postOrder.add(8);
        postOrder.add(10);
        postOrder.add(11);
        postOrder.add(9);
        postOrder.add(4);
        postOrder.add(5);
        postOrder.add(2);
        postOrder.add(6);
        postOrder.add(7);
        postOrder.add(3);
        postOrder.add(1);
        TreeNode root = buildTree2(inOrder, postOrder);
        List<Integer> res = BinaryTreeTraversal.postorderTraversal(root);
        System.out.println(res);
        root = buildTree2(inOrder, postOrder);
        res = BinaryTreeTraversal.inorderTraversal(root);
        System.out.println(res);
    }

    public static TreeNode buildTree2(List<Integer> inorder, List<Integer> postorder) {
        TreeNode root = null;
        List<Integer> leftInOrder;
        List<Integer> rightInOrder;
        List<Integer> leftPostOrder;
        List<Integer> rightPostOrder;
        int inOrderPos;
        int postOrderPos;

        if (inorder.size() > 0 && postorder.size() > 0) {
            int rootValue = postorder.get(postorder.size() - 1);
            root = new TreeNode(rootValue);

            inOrderPos = inorder.indexOf(rootValue);
            leftInOrder = inorder.subList(0, inOrderPos);
            rightInOrder = inorder.subList(inOrderPos + 1, inorder.size());

            postOrderPos = leftInOrder.size();
            leftPostOrder = postorder.subList(0, postOrderPos);
            rightPostOrder = postorder.subList(postOrderPos, postorder.size() - 1);

            root.left = buildTree2(leftInOrder, leftPostOrder);
            root.right = buildTree2(rightInOrder, rightPostOrder);
        }
        return root;
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
