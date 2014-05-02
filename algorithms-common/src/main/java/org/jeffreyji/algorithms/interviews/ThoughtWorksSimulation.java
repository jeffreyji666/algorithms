package org.jeffreyji.algorithms.interviews;

/**
 * @author: wgji
 * @date：2014年5月2日 下午11:44:37
 * @comment:
 */

public class ThoughtWorksSimulation {
    private static int a, b, c;
    private static final String Fizz = "Fizz";
    private static final String Buzz = "Buzz";
    private static final String Whizz = "Whizz";

    public static String handleFirst(int num) {
        return num % a == 0 ? Fizz : Integer.valueOf(num).toString();
    }

    public static String handleContainsFrist(int num, int first) {
        boolean contains = Integer.valueOf(num).toString().contains(Integer.valueOf(first).toString());
        return contains == true ? Fizz : Integer.valueOf(num).toString();
    }
}
