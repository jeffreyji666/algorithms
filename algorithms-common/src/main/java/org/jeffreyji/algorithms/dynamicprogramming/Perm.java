package org.jeffreyji.algorithms.dynamicprogramming; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 下午1:46:19 
 * @comment: 
 */
import java.util.HashSet;
import java.util.Set;

public class Perm {
    
    /**
     * 题目:用1、2、2、3、4、5这六个数字，用java写一个main函数，打印出所有不同的排列，如：512234、412325等.
     * 要求："4"不能在第三位，"3"与"5"不能相连
     * A(6,6)-A(5,5)-2*5*A(4,4)+2*3*A(3,3)=396,396/2=198
     * two solutions:
     * 1.Permutation
     * 2.Graph,depthFirst
     */

    public static final int BAD_INDEX = 3;
    public static final int BAD_VALUE = 4;
    public static final int FIRST_VALUE = 3;
    public static final int SECOND_VALUE = 5;
    /*use 'Set' to reject duplicate string.Maybe we should do this at the very beginning(create the string),but how?
I google it,and I find this:
1.let data = { 1, 2, 6, 3, 4, 5 };
2.get all the permutation of 'data',but only store the strings which match "str.matches("^.*6.*2.*$")" (or str.matches("^.*2.*6.*$"))
3.str.replace('6','2')
        */
    private Set<String> resultSet=new HashSet<String>();
    
    public static void main(String[] args) {
        Perm p = new Perm();
        int[] data = { 1, 2, 2, 3, 4, 5 };
        p.perm(data, 0, data.length - 1);
        Set<String> set=p.getResultSet();
        for(String str :set){
            System.out.println(str);
        }
        System.out.println(set.size());
        
    }

    //find all possible combination
    public void perm(int[] data, int begin, int end) {
        if (data == null || data.length == 0) {
            return;
        }
        if (begin == end) {
            boolean ok = check(data);//exclude the 'bad' string
            if (ok) {
                String str=stringOf(data);
                resultSet.add(str);
            }
        }
        for (int i = begin; i <= end; i++) {
            swap(data, begin, i);
            perm(data, begin + 1, end);
            swap(data, begin, i);
        }
    }

    //exclude the 'bad' string--"4"不能在第三位，"3"与"5"不能相连
       //we can also use regular expression:(!str.matches("^..4.*$")&&!str.matches("^.*((35)|(53)).*$")&&str.matches("^.*2.*6.*$"))
    public boolean check(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        for (int i = 0, len = data.length; i < len - 1; i++) {
            if (data[i] == FIRST_VALUE && data[i + 1] == SECOND_VALUE
                    || data[i + 1] == FIRST_VALUE && data[i] == SECOND_VALUE) {
                return false;
            }
            if (i + 1 == BAD_INDEX && data[i] == BAD_VALUE) {
                return false;
            }
        }
        return true;
    }

    //int[] data = { 1, 2, 2, 3, 4, 5 }-->"122345"
    public String stringOf(int[] x){
        StringBuilder sb=new StringBuilder();
        for(int i=0,len=x.length;i<len;i++){
            sb.append(x[i]);
        }
        return sb.toString();
    }
    
    public void swap(int[] x, int i, int j) {
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }
    
    public Set<String> getResultSet(){
        return resultSet;
    }
}
