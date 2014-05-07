package org.jeffreyji.algorithms.backtracking; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 下午1:35:26 
 * @comment: 
 */

public class EightQueen {

    /**
     * 八皇后问题
     * obviously,the location of a queen includes two index: row and column
     * 1.the eight queens should be put in different rows and different columns
     * 2.so,a integer array-(we name it columnIndex[8])
     *    the index of array is from 0 to 7,we can use it as the rowIndex of a location:
     *    rowIndex:     0  1  2  3  4  5  6  7 
     *    columnIndex:  a0 a1 a2 a3 a4 a5 a6 a7(well,ai=columnIndex[i])
     * 3.a0 a1 a2 a3 a4 a5 a6 a7 is a permutation of (0 1 2 3 4 5 6 7)
     * 4.we output the permutations which does not violate the rules of EightQueen:
     *   "任两个皇后都不能处于同一条横行、纵行或斜线上"
     * 5.but how to judge?
     *   let's look at this.we assume that after a permutation,the status is:
     *     i= 0 1 2 3 4 5 6 7
     *   a[i]=0 4 5 7 2 6 1 3
     *   we found that (1,4) and (2,5) share the same diagonal.
     *   that is a[i]-a[j]=i-j or a[i]-a[j]=j-i
     * 
     */
    public static void main(String[] args) {
        int MAX=8;
        int[] columnIndex=new int[MAX];
        for(int i=0;i<MAX;i++){
            columnIndex[i]=i;
        }
        eightQueen(columnIndex,0,MAX-1);//permutation(a,0,a.length-1)
        System.out.println(sum);
    }

    private static int sum;
    //produce permutation,print it if it obeys the rules of EightQueen
    public static void eightQueen(int[] columnIndex,int first,int last){
        if(first==last){
            if(check(columnIndex)){
                printQueenLocation(columnIndex);
                sum++;
            }
        }else{
            for(int i=first;i<=last;i++){
                swap(columnIndex,first,i);
                eightQueen(columnIndex,first+1,last);
                swap(columnIndex,first,i);
            }
        }
    }
    
    
    //the rule:can't be (a[i]-a[j]=i-j or a[i]-a[j]=j-1)
    public static boolean check(int[] columnIndex){
        boolean re=true;
        for(int i=0,len=columnIndex.length;i<len;i++){
            for(int j=i+1;j<len;j++){
                if((j-i==columnIndex[j]-columnIndex[i])||(i-j==columnIndex[j]-columnIndex[i])){
                    re=false;
                    break;
                }
            }
        }
        return re;
    }
    
    //print (i,j)
    public static void printQueenLocation(int[] columnIndex){
        for(int i=0,len=columnIndex.length;i<len;i++){
            System.out.print("(i,j)=("+i+","+columnIndex[i]+")");
        }
        System.out.println();
    }
    
    public static void swap(int[] a, int i, int j){
        int temp =a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}


 