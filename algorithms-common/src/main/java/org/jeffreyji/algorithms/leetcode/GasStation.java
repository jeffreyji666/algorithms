package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月25日 下午12:21:30
 * @comment:There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note: The solution is guaranteed to be unique.
 */

public class GasStation {

    public static void main(String[] args) {
        int[] gas = { 1, 2 };
        int[] cost = { 2, 1 };
        System.out.println(canCompleteCircuit(gas, cost));
    }

    //If car starts at A and can not reach B. 
    //Any station between A and B can not reach B.(B is the first station that A can not reach.)
    //If the total number of gas is bigger than the total number of cost.
    //There must be a solution.
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        assert (gas != null && gas.length > 0);
        assert (cost != null && cost.length > 0);
        int start = 0;
        int total = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            if ((tank = tank + gas[i] - cost[i]) < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }
        return (total + tank) >= 0 ? start : -1;
    }
}
