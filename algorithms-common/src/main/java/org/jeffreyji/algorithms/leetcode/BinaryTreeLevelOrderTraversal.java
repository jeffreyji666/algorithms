package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.jeffreyji.algorithms.basic.BinaryTree.TreeNode;

/**
 * @author: wgji
 * @date：2014年5月16日 上午11:42:21
 * @comment:Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
   return its level order traversal as:
    [
      [3],
      [9,20],
      [15,7]
    ]
 */

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.right = right;
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);

        ArrayList<ArrayList<Integer>> res =  new ArrayList<ArrayList<Integer>>();
        dfs(root, 0, res);
        for (ArrayList<Integer> level : res) {
            for (Integer val : level) {
                System.out.printf("%d,", val);
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        return result;
    }
    
    private static void dfs(TreeNode root, int level, ArrayList<ArrayList<Integer>> ret){
        if(root == null){
            return;
        }
        
        // 添加一个新的ArrayList表示新的一层
        if(level >= ret.size()){
            ret.add(new ArrayList<Integer>());
        }
        
        ret.get(level).add(root.val);   // 把节点添加到表示那一层的ArrayList里
        dfs(root.left, level+1, ret);       // 递归处理下一层的左子树和右子树
        dfs(root.right, level+1, ret);
    }
}
