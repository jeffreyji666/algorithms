package org.jeffreyji.algorithms.beautyOfCoding; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 上午10:42:21 
 * @comment: 
 */


import java.util.Arrays;

public class BookDiscount {

    /**编程之美 买书折扣

书上的贪心算法的分析很有意思，我看了半天看不懂，结果作者说，贪心算法在这个问题上是不适用的。。
下面用动态规划实现。
哈利波特这本书一共有五卷，每卷都是8欧元，如果读者一次购买不同的两卷可扣除5%的折扣，三卷10%，四卷20%，五卷25%。现在我要买很多本书，应该怎么组合才最省钱？
我们用F(Y1,Y2,Y3,Y4,Y5)表示这五卷书分别Yi本时的最少花销。
由于购买(2本卷一其余只购1本)和购买(2本卷二其余只购1本)(因为每卷书的价格是一样的，因此书的顺序是无所谓的)的最少花销是一样的，即F(2,1,1,1,1)=F(1,2,1,1,1)=F(1,1,2,1,1)=......。
我们用F(2,1,1,1,1)来代表这一组方案的“最小表示”，即在一个最小表示F(Y1,Y2,Y3,Y4,Y5)中满足Y1>=Y2>=Y3>=Y4>=Y5。
用动态规划我们可以建立状态转移方程：
F(Y1,Y2,Y3,Y4,Y5)
=0                                                              if(Y1=Y2=Y3=Y4=Y5=0)
=min{
        40*0.75+F(Y1-1,Y2-1,Y3-1,Y4-1,Y5-1) ,                   if(Y5>=1)
        32*0.8+F(Y1-1,Y2-1,Y3-1,Y4-1,Y5)  ,                     if(Y4>=1)
        24*0.9+F(Y1-1,Y2-1,Y3-1,Y4,Y5) ,                        if(Y3>=1)
        16*0.95+F(Y1-1,Y2-1,Y3,Y4,Y5) ,                         if(Y2>=1)
        8+F(Y1-1,Y2,Y3,Y4,Y5) ,                                 if(Y1>=1)
}
状态转移之后得到的F(Y1-1,Y2-1,Y3-1,Y4-1,Y5)等可能不是“最小表示”，要把它转化为对应的“最小表示”。

     */
    
    
    public static void main(String[] args) {
        BookDiscount bookDiscount=new BookDiscount();
        int[][] books={
                {2,2,2,1,1},
                {4,3,2,1,0}
        };
        for(int[] each:books){
            double min=bookDiscount.findMinCost(each[0],each[1],each[2],each[3],each[4]);
            System.out.println(Arrays.toString(each)+",min cost="+min);
        }
    }

    public double findMinCost(int Y1,int Y2,int Y3,int Y4,int Y5){
        int[] x={Y1,Y2,Y3,Y4,Y5};
        Arrays.sort(x);
        if(x[0]<0){
            System.out.println("购书数量不能为负数");
            return 0;
        }
        //Y1>=Y2>=Y3>=Y4>=Y5
        Y5=x[0];
        Y4=x[1];
        Y3=x[2];
        Y2=x[3];
        Y1=x[4];
        double cost=0;
        if(Y5>=1){
            cost=min(
                    8*5*(1-0.25)+findMinCost(Y1-1,Y2-1,Y3-1,Y4-1,Y5-1),
                    8*4*(1-0.20)+findMinCost(Y1-1,Y2-1,Y3-1,Y4-1,Y5),
                    8*3*(1-0.10)+findMinCost(Y1-1,Y2-1,Y3-1,Y4,Y5),
                    8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
                );
        }else if(Y4>=1){
            cost=min(
                    8*4*(1-0.20)+findMinCost(Y1-1,Y2-1,Y3-1,Y4-1,Y5),
                    8*3*(1-0.10)+findMinCost(Y1-1,Y2-1,Y3-1,Y4,Y5),
                    8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
                );
        }else if(Y3>=1){
            cost=min(
                    8*3*(1-0.10)+findMinCost(Y1-1,Y2-1,Y3-1,Y4,Y5),
                    8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
                );
        }else if(Y2>=1){
            cost=min(
                    8*2*(1-0.05)+findMinCost(Y1-1,Y2-1,Y3,Y4,Y5)
                );
        }else if(Y1>=1){//{Y1,0,0,0,0},说明买的是同一卷书，没有折扣
            cost=8*Y1;
        }else{//{0,0,0,0,0}
            cost=0;
        }
        return cost;
    }
    
    public double min(double y,double...yy){
        double m=y;
        for(double e:yy){
            if(m>e){
                m=e;
            }
        }
        return m;
    }
}

