package org.jeffreyji.algorithms.beautyOfCoding;

/** 
 * @author:  wgji
 * @date：2014年5月18日 下午3:00:52 
 * @comment: 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Point24Game {

    /**
     * 编程之美 24点游戏 
     * 1.穷举。计算所有的可能，若能得到24，输出 
     * 2.分治。引入集合，去重。另外，利用x=5=(0101)来表示(a0,a1,a2,a3)的子集(a1,a3)是非常巧妙的
     */

    private static final double DIFF = 1E-6F;
    private static final int CardsNumber = 4;
    private static final int CardMax = 13; // 扑克。最大值是13
    private static final int ResultValue = 24;

    public static void main(String[] args) throws Exception {
        int k = 0;
        while (k < 100) { // 测试100次
            int[] source = new int[CardsNumber];
            double[] number = new double[CardsNumber];
            String[] result = new String[CardsNumber]; // 记录number[i]是通过什么运算表达式得到的
            Random random = new Random();
            for (int i = 0; i < CardsNumber; i++) { // 随机生成4个数字
                int x = random.nextInt(CardMax) + 1;
                source[i] = x;
                number[i] = x;
                result[i] = "" + x;
            }
            // 打印参与运算的数字
            System.out.println(Arrays.toString(source));
            // 方法1
            boolean ok = Point24Game.compute(number, result, CardsNumber);
            if (ok) {
                System.out.println("1. " + result[0]);
            }
            // 方法2
            boolean ok2 = Point24Game.compute(source);
            if (ok != ok2) { // 两种方法得到结果（能否生成24）应该是一致的
                throw new Exception("something wrong");
            }
            System.out.println("===================================");
            k++;
        }

    }

    // 方法1
    public static boolean compute(double[] number, String[] result, int n) {
        if (n == 1) {
            return (Math.abs(number[0] - ResultValue) < DIFF); // number[0]==ResultValue or not
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double a = number[i];
                double b = number[j];

                String expa = result[i];
                String expb = result[j];

                // 每计算一次，参与运算的数字就少一个。将最后一个数字复制到位置j,可达到缩小（缩短）数组的效果
                number[j] = number[n - 1];
                result[j] = result[n - 1];

                // 加法操作 a+b
                number[i] = a + b;
                result[i] = '(' + expa + '+' + expb + ')';
                if (compute(number, result, n - 1)) {
                    return true;
                }

                // 减法操作 a-b
                number[i] = a - b;
                result[i] = '(' + expa + '-' + expb + ')';
                if (compute(number, result, n - 1)) {
                    return true;
                }

                // 减法操作 b-a
                number[i] = b - a;
                result[i] = '(' + expb + '-' + expa + ')';
                if (compute(number, result, n - 1)) {
                    return true;
                }

                // 乘法操作 a*b
                number[i] = a * b;
                result[i] = '(' + expa + '*' + expb + ')';
                if (compute(number, result, n - 1)) {
                    return true;
                }

                // 除法操作 a/b, 如果除数不为0
                if (b != 0) {
                    number[i] = a / b;
                    result[i] = '(' + expa + '/' + expb + ')';
                    if (compute(number, result, n - 1)) {
                        return true;
                    }
                }

                // 除法操作 b/a , 如果除数不为0
                if (a != 0) {
                    number[i] = b / a;
                    result[i] = '(' + expb + '/' + expa + ')';
                    if (compute(number, result, n - 1)) {
                        return true;
                    }
                }

                number[i] = a;
                number[j] = b;
                result[i] = expa;
                result[j] = expb;
            }
        }
        return false;
    }

    // 方法2
    private static final int n = CardsNumber;
    private static final int m = (1 << n) - 1; // (10000)-1=(1111)=15
    @SuppressWarnings("unchecked")
    private static List<Node>[] S = new ArrayList[m + 1];

    public static boolean compute(int[] number) {
        // (a0,a1,a2,a3)的子集(a0),(a1),(a2),(a3)只有一个元素，不用运算直接返回即可
        for (int j = 0; j < n; j++) {
            int v = number[j];
            Node node = new Node((0.0 + v), ("" + v));
            List<Node> Si = new ArrayList<Node>();
            Si.add(node);
            int i = 1 << j;
            S[i] = Si;
        }

        for (int i = 1; i <= m; i++) {
            List<Node> Si = fun(i);
            S[i] = Si;
        }

        List<Node> Sm = S[m];
        for (Node v : Sm) {
            if (Math.abs(v.value - ResultValue) < DIFF) {
                System.out.println("2. " + v.exp);
                return true;
            }
        }

        return false;
    }

    // 递归求S[i]
    public static List<Node> fun(int i) {
        List<Node> si = S[i];
        if (si != null && !si.isEmpty()) {
            return si;
        }
        si = new ArrayList<Node>();
        for (int x = 1; x < i; x++) {
            if ((x & i) == x) { // 确保x是i的子集
                List<Node> sx = fun(x);
                List<Node> s_x = fun(i - x);
                si.addAll(fork(sx, s_x));
            }

        }
        return si;
    }

    // 集合里元素两两运算并去重
    public static List<Node> fork(List<Node> sx, List<Node> s_x) {
        Set<Node> set = new HashSet<Node>();
        for (int i = 0, m = sx.size(); i < m; i++) {
            for (int j = 0, n = s_x.size(); j < n; j++) {
                Node a = sx.get(i);
                Node b = s_x.get(j);
                set.add(new Node(a.value + b.value, "(" + a.exp + "+" + b.exp + ")"));
                set.add(new Node(a.value - b.value, "(" + a.exp + "-" + b.exp + ")"));
                set.add(new Node(b.value - a.value, "(" + b.exp + "-" + a.exp + ")"));
                set.add(new Node(a.value * b.value, "(" + a.exp + "*" + b.exp + ")"));
                if (b.value != 0) {
                    set.add(new Node(a.value / b.value, "(" + a.exp + "/" + b.exp + ")"));
                }
                if (a.value != 0) {
                    set.add(new Node(b.value / a.value, "(" + b.exp + "/" + a.exp + ")"));
                }
            }
        }
        List<Node> si = new ArrayList<Node>(set);
        return si;
    }

    static class Node {
        double value; // 表达式的值
        String exp; // 表达式

        Node(double value, String exp) {
            this.value = value;
            this.exp = exp;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Node))
                return false;
            Node node = (Node) o;
            return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(node.value);
            // &&this.exp.equals(node.exp); //只关心值是否相等，不关心表达式
        }

        public int hashCode() {
            int result = 17;
            long tolong = Double.doubleToLongBits(value);
            result = 37 * result + (int) (tolong ^ (tolong >>> 32));
            // result = 37 * result + exp.hashCode();
            return result;
        }
    }
}
