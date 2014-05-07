package org.jeffreyji.algorithms.util; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 下午1:36:45 
 * @comment: 
 */

public class PalindromeInt {
    /**
     * PalindromeInt,like 1,121,12321....
     * you should consider the possibility that the reversed number might overflow
     * eg. 1...................9,after reversing,9 comes to first,and...you know that.
     * 
     */
    public static void main(String[] args) {
        int[] a={12321,17770};
        PalindromeInt pi=new PalindromeInt();
        boolean re=pi.isPalindromeInt2(a[0]);
        System.out.println(re);
        re=pi.isPalindromeInt2(a[1]);
        System.out.println(re);
    }

    //generic solution 
    public boolean isPalindromeInt(int x){
        if(x<0)return false;
        int x2=x;
        int y=0;
        while(x>0){
            y*=10;
            y+=x%10;
            x/=10;
        }
        return x2==y;
    }
    
    //better solution
    //avoid overflow
    public boolean isPalindromeInt2(int x){
        if(x<0)return false;
        boolean re=true;
        int div=1;
        while(x/div>=10){
            div*=10;
        }
        while(x>0){
            int h=x/div;//head
            int t=x%10;//tail
            if(h!=t){
                re=false;
                break;
            }
            x=(x%div)/10;//now x is 232 instead of 12321
            div/=100;//accordingly,div should be 100 instead of 10000
        }
        return re;
    }
    
    /* c/c++? I don't know how it works
     * invoke like that: isPalindrome(x, x)
    boolean isPalindrome(int x,int &y){
        if (x < 0) return false;
          if (x == 0) return true;
          if (isPalindrome(x/10, y) && (x%10 == y%10)) {
            y /= 10;
            return true;
          } else {
            return false;
          }
    }
    */
    
}


