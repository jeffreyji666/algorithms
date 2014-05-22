package org.jeffreyji.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wgji
 * @date：2014年5月22日 下午4:56:41
 * @comment:Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree is symmetric:
    
        1
       / \
      2   2
     / \ / \
    3  4 4  3
    But the following is not:
        1
       / \
      2   2
       \   \
       3    3
    Note:Bonus points if you could solve it both recursively and iteratively.   
 */

public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;

        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        right.left = new TreeNode(4);
        right.right =  new TreeNode(3);

        System.out.println(isSymmetric2(root));
        
        root = new TreeNode(1);
        left = new TreeNode(2);
        right = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.right = new TreeNode(3);
        right.right = new TreeNode(3);
        System.out.println(isSymmetric2(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        
        return isSymmetric(root.left,root.right);
    }
    
    private static boolean isSymmetric(TreeNode root1, TreeNode root2){
        if( (root1 == null && root2 != null) || (root1 != null && root2 == null) ){
            return false;
        }
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1.val != root2.val){
            return false;
        }
        return isSymmetric(root1.left,root2.right) && isSymmetric(root1.right,root2.left);
    }
    
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> left = new LinkedList<TreeNode>();
        left.offer(root.left);
        Queue<TreeNode> right = new LinkedList<TreeNode>();
        right.offer(root.right);

        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode lnode = left.poll();
            TreeNode rnode = right.poll();

            if ((lnode == null && rnode != null) || (lnode != null && rnode == null)) {
                return false;
            }

            if (lnode == null && rnode == null) {
                continue;
            }
            if (lnode.val != rnode.val) {
                return false;
            }
            left.offer(lnode.left);
            left.offer(lnode.right);
            right.offer(rnode.right);
            right.offer(rnode.left);

        }
        return true;
    }
}
