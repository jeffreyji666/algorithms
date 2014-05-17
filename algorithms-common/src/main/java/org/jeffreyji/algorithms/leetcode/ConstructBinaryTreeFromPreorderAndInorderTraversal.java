package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.jeffreyji.algorithms.basic.BinaryTree.TreeNode;

/**
 * @author: wgji
 * @date：2014年5月17日 下午2:58:37
 * @comment: Given preorder and inorder traversal of a tree, construct the binary tree. 
 * Note:You may assume that duplicates do not exist in the tree.
 */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        List<Integer> preOrder = new ArrayList<Integer>();
        preOrder.add(1);
        preOrder.add(2);
        preOrder.add(4);
        preOrder.add(8);
        preOrder.add(9);
        preOrder.add(10);
        preOrder.add(11);
        preOrder.add(5);
        preOrder.add(3);
        preOrder.add(6);
        preOrder.add(7);
        
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
        rebuildBinaryTreeRec(preOrder,inOrder);
        
        int[] preorder = { 1, 2, 4, 8, 9, 10, 11, 5, 3, 6, 7 };
        int[] inorder = { 8, 4, 10, 9, 11, 2, 5, 1, 6, 3, 7 };
        buildTree2(preorder, inorder);
    }

    public static TreeNode rebuildBinaryTreeRec(List<Integer> preOrder, List<Integer> inOrder){
        TreeNode root = null;
        List<Integer> leftPreOrder;
        List<Integer> rightPreOrder;
        List<Integer> leftInorder;
        List<Integer> rightInorder;
        int inorderPos;
        int preorderPos;
 
        if ((preOrder.size() != 0) && (inOrder.size() != 0))
        {
            // 把preorder的第一个元素作为root
            root = new TreeNode(preOrder.get(0));
 
            //  Based upon the current node data seperate the traversals into leftPreorder, rightPreorder,
            //  leftInorder, rightInorder lists
            // 因为知道root节点了，所以根据root节点位置，把preorder，inorder分别划分为 root左侧 和 右侧 的两个子区间
            inorderPos = inOrder.indexOf(preOrder.get(0));      // inorder序列的分割点
            leftInorder = inOrder.subList(0, inorderPos);
            rightInorder = inOrder.subList(inorderPos + 1, inOrder.size());
 
            preorderPos = leftInorder.size();                           // preorder序列的分割点
            leftPreOrder = preOrder.subList(1, preorderPos + 1);
            rightPreOrder = preOrder.subList(preorderPos + 1, preOrder.size());
 
            root.left = rebuildBinaryTreeRec(leftPreOrder, leftInorder);        // root的左子树就是preorder和inorder的左侧区间而形成的树
            root.right = rebuildBinaryTreeRec(rightPreOrder, rightInorder); // root的右子树就是preorder和inorder的右侧区间而形成的树
        }
 
        return root;
    }
    
    private static int findPosition(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private static TreeNode myBuildTree(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        int position = findPosition(inorder, instart, inend, preorder[prestart]);

        root.left = myBuildTree(inorder, instart, position - 1, preorder, prestart + 1, prestart + position - instart);
        root.right = myBuildTree(inorder, position + 1, inend, preorder, position - inend + preend + 1, preend);
        return root;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) {
            return null;
        }
        return myBuildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
}
