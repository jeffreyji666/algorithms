package org.jeffreyji.algorithms.beautyOfCoding; 

/** 
 * @author:  wgji
 * @date：2014年5月7日 上午10:42:53 
 * @comment: 
 */

public class AptElevator {

    /**
     * 编程之美 小飞 电梯调度算法
     * 在繁忙的时间，每次电梯从一层往上走时，我们只允许电梯停在其中的某一层。
     * 所有乘客都从一楼上电梯，到达某层楼后，电梯听下来，所有乘客再从这里爬楼梯到自己的目的层。
     * 在一楼时，每个乘客选择自己的目的层，电梯则自动计算出应停的楼层。
     * 问：电梯停在哪一层楼，能够保证这次乘坐电梯的所有乘客爬楼梯的层数之和最少？
     
        思路:当电梯停靠在第i层时,乘客所要爬的总的楼梯数为Y.
        假设有N1个乘客要到达的层数<i,有N2个乘客要到达的层数==i,有N3个乘客要到达的层数>i.
        所以有:
        (1)当电梯改停在i-1,则 Y+(N2+N3-N1)
        (2)当电梯改停在i+1,则 Y+(N1+N2-N3)
        所以当后面那部分的值<0时(如(2)的N1+N2<N3),则加上负数后总的楼梯数比原来的小,即更优解.
        因此,我们可以从第一层开始,用以上策略,考察N1,N2,N3的值,依次调整以得到最优解.
     */
    
    private int targetFloor;//电梯应该停靠在哪一层
    private int minStairs;//不能直达的乘客共要走多少层楼梯
    
    public static void main(String[] args) {
        AptElevator elevator=new AptElevator();
        int[] person={0,2,5,7,8,9,6,6,1,4,4,8,5,2,4,5,8,6,3,3,5,9,9,6,6,9,8,8,5,5,9,6,6,3};//person[i]表示要到第i层的人数
        elevator.opt(person);
        System.out.println(elevator.targetFloor+","+elevator.minStairs);
    }

    public AptElevator(){
        this.targetFloor=0;
        this.minStairs=0;
    }
    
    public void opt(int[] person){
        if(person==null){
            return;
        }
        if(person.length<2){//如果没有人到二楼或者更高，那就不用坐电梯了
            return;
        }
        int N=person.length-1;
        targetFloor=2;//先让电梯停在二楼，计算N1,N2
        int N1=0;
        int N2=person[2];
        int N3=0;
        //电梯停在二楼，计算N3以及总楼梯数MinFloor
        for(int j=3;j<=N;j++){
            N3+=person[j];
            minStairs+=person[j]*(j-targetFloor);
        }
        //让电梯依次改停在3楼、4楼...N楼,出现更优解时,则调整电梯停靠层数以及N1,N2,N3
        for(int i=3;i<=N;i++){
            if(N1+N2<N3){
                targetFloor=i;
                N1=N1+N2;
                N2=person[i];
                N3=N3-person[i];
                minStairs+=N1+N2-N3;
            }
        }
    }
    
}