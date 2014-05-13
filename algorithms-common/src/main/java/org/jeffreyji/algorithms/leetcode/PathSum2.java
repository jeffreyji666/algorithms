package org.jeffreyji.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wgji
 * @date：2014年5月13日 下午4:10:22
 * @comment:Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
    return
    [
       [5,4,11,2],
       [5,8,4,5]
    ]
 */

public class PathSum2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(8);
        TreeNode left2 = new TreeNode(11);
        TreeNode left3 = new TreeNode(13);
        TreeNode right2 = new TreeNode(4);

        root.left = left1;
        root.right = right1;
        left1.left = left2;
        right1.left = left3;
        right1.right = right2;
        left2.left = new TreeNode(7);
        left2.right = new TreeNode(2);
        right2.left = new TreeNode(5);
        right2.right = new TreeNode(1);

        List<ArrayList<Integer>> res = pathSum(root, 22);
        for(ArrayList<Integer> list:res){
            for(Integer item :list){
                System.out.printf("%d,", item);
            }
            System.out.println();
        }
    }
    
    public static List<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null){
            return res;
        }
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        recursion(root, res, tmp, sum);
        return res;
    }

    public static void recursion(TreeNode root, List<ArrayList<Integer>> res, ArrayList<Integer> tmp, int sum) {
        if (root.left == null && root.right == null) {
            tmp.add(root.val);
            int sum1 = 0;
            for (int i = 0; i < tmp.size(); i++) {
                sum1 += tmp.get(i);
            }
            if (sum1 == sum) {
                res.add(new ArrayList<Integer>(tmp));
            }
            return;
        }
        tmp.add(root.val);
        if (root.left != null) {
            recursion(root.left, res, tmp, sum);
            tmp.remove(tmp.size() - 1);
        }
        if (root.right != null) {
            recursion(root.right, res, tmp, sum);
            tmp.remove(tmp.size() - 1);
        }
    }
}
