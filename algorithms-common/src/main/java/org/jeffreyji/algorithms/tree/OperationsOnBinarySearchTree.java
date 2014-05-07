package org.jeffreyji.algorithms.tree; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 上午10:44:13 
 * @comment: 
 */
import java.util.LinkedList;
public class OperationsOnBinarySearchTree {

    /**
     * It shows the operations on Binary Search Tree.
     * see also: http://blog.csdn.net/jiqiren007/article/details/6534810
     */
    private Node root;
    public static void main(String[] args) {
        int[] data={12,5,18,2,9,15,19,0,0,8,0,0,17,};
      /*    12                                                     
           /   \                                                    
          5     18                                                   
        /   \   / \
       2     9 15  19
            /    \
           8      17
       */
    }
    
    public void delete(int dataDelete){
        if(root==null){
            return;
        }
        Node curNode=root;
        NodePair pair=findNodeAndParent(curNode,dataDelete);
        Node nodeDelete=pair.son;
        Node parent=pair.parent;
        if(nodeDelete==null){
            return;
        }
        if(isLeaf(nodeDelete)){
            if(parent.getLeft()==nodeDelete){
                parent.setLeft(null);
            }
            if(parent.getRight()==nodeDelete){
                parent.setRight(null);
            }
        }else{
            if( hasLeftOnly(nodeDelete) ){
                if(parent.getLeft()==nodeDelete){
                    parent.setLeft(nodeDelete.getLeft());
                }
                if(parent.getRight()==nodeDelete){
                    parent.setRight(nodeDelete.getLeft());
                }
            }else if( hasRightOnly(nodeDelete)  ){
                if(parent.getLeft()==nodeDelete){
                    parent.setLeft(nodeDelete.getRight());
                }
                if(parent.getRight()==nodeDelete){
                    parent.setRight(nodeDelete.getRight());
                }
            }else{//has both left child and right child.Successor is in the min(curNode.getRight())
                NodePair tmpPair=min(nodeDelete.getRight());
                Node successor=tmpPair.son;
                Node sParent=tmpPair.parent;
                nodeDelete.setData(successor.getData());
                if(null==sParent){
                    nodeDelete.setRight(null);
                }else{
                    sParent.setLeft(successor.getRight());
                }
            }
        }
        
        
    }
    
    public NodePair findNodeAndParent(Node curNode,int data){
        if(curNode==null){
            return null;
        }
        Node parent=null;
        Node son=null;
        NodePair pair=null;
        while(curNode!=null){
            int curData=curNode.getData();
            if(curData==data){
                son=curNode;//when curNode.getData()==data,'parent' is null.Is it OK?
                break;
            }
            if(data<curData){
                parent=curNode;
                curNode=curNode.getLeft();
            }
            if(data>curData){
                parent=curNode;
                curNode=curNode.getRight();
            }
        }
        pair=new NodePair(son,parent);
        return pair;
    }
    public boolean hasLeftOnly(Node node){
        return node!=null&&node.getLeft()!=null&&node.getRight()==null;
    }
    public boolean hasRightOnly(Node node){
        return node!=null&&node.getRight()!=null&&node.getLeft()==null;
    }
    public boolean isLeaf(Node node){
        return node!=null&&node.getLeft()==null&&node.getRight()==null;
    }
    public NodePair min(Node curNode){
        if(curNode==null){
            return null;
        }
        Node parent=null;
        while(curNode.getLeft()!=null){//when 'curNode' has no left child,'curNode' is min,and its parent is null(ok?)
            parent=curNode;
            curNode=curNode.getLeft();
        }
        return new NodePair(curNode,parent);
    }
    
    //we don't get 'max''s parent node like 'min'
    public Node max(Node curNode){
        if(curNode==null){
            return null;
        }
        while(curNode.getRight()!=null){
            curNode=curNode.getRight();
        }
        return curNode;
    }
    
    
    public Node find(int target){
        if(root==null){//empty tree
            return null;
        }else{
            return findHelp(root,target);
        }
    }
    public Node findHelp(Node curNode,int target){
        Node result=null;
        int curData=curNode.getData();
        if(target==curData){
            result=curNode;
        }
        if(target<curData){
            findHelp(curNode.getLeft(),target);
        }
        if(target>curData){
            findHelp(curNode.getRight(),target);
        }
        return result;
    }
    
    public void insert(int dataInsert){
        if(root==null){//the tree is empty
            root=new Node(dataInsert);
        }else{
            insertHelp(root,dataInsert);
        }
    }
    
    public void insertHelp(Node curNode,int dataInsert){
        Node nodeToInsert=new Node(dataInsert);
        int curData=curNode.getData();
        if(dataInsert<=curData){//insert into left tree
            Node left=curNode.getLeft();
            if(left==null){
                curNode.setLeft(nodeToInsert);
            }else{
                insertHelp(left,dataInsert);
            }
        }
        if(dataInsert>curData){//insert into right tree
            Node right=curNode.getRight();
            if(right==null){
                curNode.setRight(nodeToInsert);
            }else{
                insertHelp(right,dataInsert);
            }
        }
    }
        
    public void levelTraverse(){
        if(root==null){
            return;
        }
        Node node=root;
        LinkedList<Node> queue=new LinkedList<Node>();
        queue.addLast(node);
        while(!queue.isEmpty()){
            node=queue.removeFirst();
            System.out.print(node.getData()+" ");
            if(node.getLeft()!=null){
                queue.addLast(node.getLeft());
            }
            if(node.getRight()!=null){
                queue.addLast(node.getRight());
            }
        }
        System.out.println();
    }
    
    public void inOrder(Node curNode){
        if(curNode==null){
            return;
        }
        inOrder(curNode.getLeft());
        System.out.print(curNode.getData()+" ");
        inOrder(curNode.getRight());
    }
    //when deleting a node,we need the node and its parent.
    private static class NodePair{
        
        Node son;
        Node parent;
        
        NodePair(Node son,Node parent){
            this.son=son;
            this.parent=parent;
        }
        
    }
    
}
class Node {
    
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
