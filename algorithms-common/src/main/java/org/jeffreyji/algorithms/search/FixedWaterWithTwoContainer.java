package org.jeffreyji.algorithms.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * @author: wgji
 * @date：2014年3月28日 上午10:12:41
 * @comment: 记忆化搜索，也就是搜过的状态不要再搜。 这也挺类似与dp，不过神搜比dp的好处就是构造解超容易，而且很容易根据题目对解的要求，来构造解。 Jugs Time Limit:1000MS Memory
 *           Limit:65536K Total Submit:836 Accepted:549 Special Judged Description In the movie "Die Hard 3", Bruce
 *           Willis and Samuel L. Jackson were confronted with the following puzzle. They were given a 3-gallon jug and
 *           a 5-gallon jug and were asked to fill the 5-gallon jug with exactly 4 gallons. This problem generalizes
 *           that puzzle.
 * 
 *           You have two jugs, A and B, and an infinite supply of water. There are three types of actions that you can
 *           use: (1) you can fill a jug, (2) you can empty a jug, and (3) you can pour from one jug to the other.
 *           Pouring from one jug to the other stops when the first jug is empty or the second jug is full, whichever
 *           comes first. For example, if A has 5 gallons and B has 6 gallons and a capacity of 8, then pouring from A
 *           to B leaves B full and 3 gallons in A.
 * 
 *           A problem is given by a triple (Ca,Cb,N), where Ca and Cb are the capacities of the jugs A and B,
 *           respectively, and N is the goal. A solution is a sequence of steps that leaves exactly N gallons in jug B.
 *           The possible steps are
 * 
 *           fill A 
 *           fill B 
 *           empty A 
 *           empty B 
 *           pour A B 
 *           pour B A 
 *           success
 * 
 *           where "pour A B" means "pour the contents of jug A into jug B", and "success" means that the goal has been
 *           accomplished.
 * 
 *           You may assume that the input you are given does have a solution. 
 *           Input 
 *           Input to your program consists of a
 *           series of input lines each defining one puzzle. 
 *           Input for each puzzle is a single line of three positive
 *           integers: Ca, Cb, and N. Ca and Cb are the capacities of jugs A and B, and N is the goal. You can assume 0
 *           < Ca <= Cb and N <= Cb <=1000 and that A and B are relatively prime to one another. 
 *           Output 
 *           Output from your
 *           program will consist of a series of instructions from the list of the potential output lines which will
 *           result in either of the jugs containing exactly N gallons of water. The last line of output 
 *           for each puzzle
 *           should be the line "success". 
 *           Output lines start in column 1 and there should be no empty lines nor any
 *           trailing spaces. 
 *           Sample Input 3 5 4 
 *                 5 7 3 
 *           Sample Output 
 *           fill B 
 *           pour B A 
 *           empty A 
 *           pour B A 
 *           fill B 
 *           pour B A
 *           success 
 *           
 *           fill A 
 *           pour A B 
 *           fill A 
 *           pour A B 
 *           empty B 
 *           pour A B 
 *           success 
 *           
 *           Source
 */

public class FixedWaterWithTwoContainer {
    static int ca, cb, target;
    static boolean[][] vst;

    public static void main(String[] args) throws Exception {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = cin.readLine();
            String[] sa = s.split(" ");
            ca = Integer.parseInt(sa[0]);
            cb = Integer.parseInt(sa[1]);
            target = Integer.parseInt(sa[2]);
            vst = new boolean[ca + 1][cb + 1];
            result = new LinkedList<String>();
            try {
                dfs(0, 0);
            } catch (Exception e) {
                for (String t : result) {
                    System.out.println(t);
                }
            }
        }
    }

    static LinkedList<String> result;

    public static void dfs(int a, int b) throws Exception {
        if (a == target || b == target) {
            result.add("success");
            throw new Exception();
        }
        if (vst[a][b])
            return;
        vst[a][b] = true;
        if (a < ca) {
            result.add("fill A");
            dfs(ca, b);
            result.removeLast();
        }
        if (b < cb) {
            result.add("fill B");
            dfs(a, cb);
            result.removeLast();
        }
        if (a > 0) {
            result.add("empty A");
            dfs(0, cb);
            result.removeLast();
        }
        if (b > 0) {
            result.add("empty B");
            dfs(a, 0);
            result.removeLast();
        }
        if (a < ca && b > 0) {
            result.add("pour B A");
            if (a + b <= ca)
                dfs(a + b, 0);
            else
                dfs(ca, a + b - ca);
            result.removeLast();
        }
        if (b < cb && a > 0) {
            result.add("pour A B");
            if (a + b <= cb)
                dfs(0, a + b);
            else
                dfs(a + b - cb, cb);
            result.removeLast();
        }
    }
}
