package org.jeffreyji.algorithms.graph; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 上午10:41:22 
 * @comment: 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GraphColoringProblem {

    /**编程之美 高效地安排会议 图着色问题 贪心算法
     * 假设要用很多个教室对一组活动进行调度。我们希望使用尽可能少的教室来调度所有的活动。请给出一个有效的贪心算法，来确定哪一个活动应使用哪一个教室。
     * (这个问题也被成为区间图着色(interval-graph coloring)问题。我们可作出一个区间图，其顶点为已知的活动，其边连接着不兼容的活动。
     * 为使任两个相邻结点的颜色均不相同，所需的最少颜色对应于找出调度给定的所有活动所需的最少教室数。)
     * see
     * http://renren.it/a/caozuoxitong/OS/20120626/176363.html
     */
    public static void main(String[] args) {
        List<Meeting> list=new ArrayList<Meeting>();
        Random random=new Random();
        for(int i=0;i<10;i++){
            int begin=random.nextInt(10)+1;
            int end=begin+(random.nextInt(10)+1);
            Meeting meeting=new Meeting(begin,end);
            list.add(meeting);
        }
        GraphColoringProblem gcp=new GraphColoringProblem();
        gcp.smartManagment(list);
    }

    public void smartManagment(List<Meeting> list){
        if(list==null||list.size()<2){
            return;
        }
        printList(list);
        Collections.sort(list);
        printList(list);
        List<List<Meeting>> outerList=new ArrayList<List<Meeting>>();
        while(list.size()!=0){
            int size=list.size();
            List<Meeting> listOfOneRoom=new ArrayList<Meeting>();
            Meeting current=list.get(0);
            listOfOneRoom.add(current);
            for(int i=1;i<size;i++){
                Meeting candidate=list.get(i);
                if(candidate.begin>=current.end){
                    listOfOneRoom.add(candidate);
                    current=candidate;
                }
            }
            outerList.add(listOfOneRoom);
            list.removeAll(listOfOneRoom);
        }
        //meetings that can be held in the same room are printed in one line:
        for(int i=0,size=outerList.size();i<size;i++){
            printList(outerList.get(i));
        }
    }
    
    static class Meeting implements Comparable<Meeting>{
        int begin;
        int end;
        Meeting(int begin,int end){
            this.begin=begin;
            this.end=end;
        }
        public int compareTo(Meeting o) {
            int endCmp=this.end-o.end;
            if(endCmp==0){
                return this.begin-o.begin;
            }
            return endCmp;
        }
        public String toString(){
            return "["+begin+","+end+"]";
        }
    }
    
    public static void printList(List<Meeting> list){
        if(list==null||list.size()==0){
            return;
        }
        for(int i=0,size=list.size();i<size;i++){
            System.out.print(list.get(i));
        }
        System.out.println();
    }
}

