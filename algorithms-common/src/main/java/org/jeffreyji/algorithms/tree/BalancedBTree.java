package org.jeffreyji.algorithms.tree; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 下午1:39:56 
 * @comment: 
 */
public class BalancedBTree {

    /**
     * Q判断二叉树是不是平衡
        1
       / \
      2   3
     / \   \
    4   5   6
       /
      7
     */
    public static void main(String[] args) {

        int[][] threeBTrees={
                {1,2,3,4,5,0,6,0,0,7},//balanced
                {1,2,3,4,5,0,0,0,0,7},//not balanced
                };
        for(int[] each:threeBTrees){
            Node node= new Node(0);
            AuxClass aux= new AuxClass();
            boolean result=isBalanced(node,aux);
            System.out.println(result);
        }
    }

    /*
     * 如果我们用后序遍历的方式遍历二叉树的每一个结点，在遍历到一个结点之前我们已经遍历了它的左右子树。
     * 只要在遍历每个结点的时候记录它的深度（某一结点的深度等于它到叶节点的路径的长度），
     * 我们就可以一边遍历一边判断每个结点是不是平衡的
     * C/C++代码可参见 http://zhedahht.blog.163.com/blog/static/25411174201142733927831/
     * 由于Java无法像C那样“传递参数的地址，函数返回时能得到参数的值”，唯有新建一个辅助类：AuxClass
     */
    public static boolean isBalanced(Node node,AuxClass aux){
        if(node==null){
            aux.depth=0;
            return true;
        }
        AuxClass left=new AuxClass();
        AuxClass right=new AuxClass();
        //get leftTreeDepth and rightTreeDepth of a node.If the 'diff' is bigger than 1,return false;true otherwise
        if(isBalanced(node.getLeft(),left)&&isBalanced(node.getRight(),right)){
            int leftDepth=left.depth;
            int rightDepth=right.depth;
            int diff=leftDepth-rightDepth;
            if(diff==1||diff==-1||diff==0){
                aux.depth=leftDepth>rightDepth?leftDepth+1:rightDepth+1;
                return true;
            }
        }
        return false;
    }
    public static class AuxClass{
        private int depth;
    }
}



 