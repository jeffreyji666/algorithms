package org.jeffreyji.algorithms.toutiao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
/**
 * @author jiwengang
 * @since 2019/3/28 上午12:19
 */
public class LCA {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果找到 p 或者 q 那么就没有必要接着递归，因为共同祖先只可能是该节点或该节点祖先
        //如果 root 为空了，说明这条路径上不可能有 p 或 q 节点，返回空
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);  //往左分支上寻找
        TreeNode right = lowestCommonAncestor(root.right, p, q);  //往右分支上寻找
        if (left != null && right != null) {
            return root;  //说明 p 和 q 是分布在 root 两侧，返回即可
        }
        if (left != null) {
            return left;  //说明在 left 分支上找到 p 或 q 节点，返回即可
        }
        return right;  //否则返回 right
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> parent = new HashMap();
        parent.put(root, null);
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {  //找到 p 和 q 的父节点
            root = queue.poll();
            if (root != null) {
                parent.put(root.left, root);
                parent.put(root.right, root);
                queue.add(root.left);
                queue.add(root.right);
            }
        }
        HashSet<TreeNode> set = new HashSet();
        while (p != null) {  //将 p 的所有父节点放入 set 中
            set.add(p);
            p = parent.get(p);
        }
        while (!set.contains(q)) {  //找到公共父节点就返回
            q = parent.get(q);
        }
        return q;
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
