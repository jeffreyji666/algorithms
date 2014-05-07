package org.jeffreyji.algorithms.beautyOfCoding; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 上午10:41:47 
 * @comment: 
 */

import java.util.Arrays;
import java.util.Random;

public class BeverageSupply {

    /**
     * 编程之美 饮料供货
     * 设Opt（V’，i）表示从i到n-1种饮料中，总容量为V’的方案中，满意度之和的最大值。
     * 那么递归式就应该是：Opt（V’，i）=max{ k * Hi+Opt（V’-Vi * k，i+1）}（k=0，1，2…，Ci，i=0，1，2…，n-1）
     * 其中Ci为第i种饮料的最大供应量
     * 
     * 想了好久都没法理解书上这个递推公式里面的i+1，为什么是i+1而不是其他？
     * 后来我转换了一下，代码本质上是一样的，只是比较符合我自己的思路：
     * opt[i][j] i代表供货总容量，j代表饮料种类。假设容量都是整数。i和j都从1开始考虑：
     * 当只有一种饮料时，容易求得各种总容量对应的最大满意度；当新增加一种饮料时，将一部分容量用新饮料代替，求得新的满意度；
     * 将新的满意度与旧满意度比较，如果新结果较大就更新。
     * 如何由opt[i][j-1]求得opt[i][j]呢？
     * --将一部分容量分给第j种饮料，那新的满意度就是(opt[i-k*v][j]+k*value),其中v代表第j种饮料每瓶的容量,k代表瓶数,value代表满意度
     */
    
    public static void main(String[] args) {
        int T=3;
        int V=10;
        BeverageSupply beverageSupply=new BeverageSupply();
        Beverage[] beverages=beverageSupply.initBeverage(V, T);
        int maxValue=beverageSupply.maxValue(beverages,V, T);
        System.out.println("总容量："+V+" 最大满意度="+maxValue);
    }
    
    public int maxValue(Beverage[] beverages,int V,int T){
        
        if(beverages==null||beverages.length==0){
            return -1;
        }
        if(V<0){
            return -1;
        }
        if(T<0 || T>beverages.length){      //beverages' index starts with 1
            return -1;
        }
        
        int[][] opt=new int[V+1][T+1];
    
        for(int j=1;j<=T;j++){
            int c=beverages[j].count;
            int v=beverages[j].volume;
            int value=beverages[j].value;
            for(int i=1;i<=V;i++){
                for(int k=0;k<=c;k++){
                    if(i<k*v){
                        break;
                    }
                    int x=opt[i-k*v][j-1];
                    int y=x+k*value;
                    if(y>=opt[i][j]){
                        opt[i][j]=y;
                    }
                }
            }
        }
//      myprint(opt);
        return opt[V][T];
    }
    
    static class Beverage{
        int value;      //满意度
        int volume;     //每瓶的容量
        int count;      //最多可提供多少瓶
        Beverage(int value,int volume,int count){
            this.value=value;
            this.volume=volume;
            this.count=count;
        }
    }
    
    public Beverage[] initBeverage(int V,int T){
        if( V<0 || T<0 ){
            return new Beverage[0];
        }
        Beverage[] beverages=new Beverage[T+1];
        Random random=new Random();
        System.out.println("满意度 容量（每瓶）  最多可提供数量（瓶）");
        for(int i=0;i<T;i++){
            //value volume count随机生成
            int value=random.nextInt(T)+1;
            int volume=random.nextInt(V/2)+1;
            int count=random.nextInt(V/2)+1;
            beverages[i+1]=new Beverage(value,volume,count);
            System.out.println(value+"      "+volume+"      "+count);
        }
        return beverages;
    }
    
    public void myprint(int[][] array){
        if(array!=null){
            for(int[] a:array){
                System.out.println(Arrays.toString(a));
            }
        }
    }
}
 