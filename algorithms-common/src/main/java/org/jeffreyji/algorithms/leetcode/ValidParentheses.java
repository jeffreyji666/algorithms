package org.jeffreyji.algorithms.leetcode;

import java.util.Stack;

/**
 * @author: wgji
 * @date：2014年5月15日 上午11:04:35
 * @comment:Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("(){}[]([{}])"));
    }

    public static boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }

        Stack<Character> st = new Stack<Character>();
        st.push(s.charAt(0));

        for (int i = 1; i < s.length(); ++i) {
            if (!st.empty() && isMatch(st.peek(), s.charAt(i))) {
                st.pop();
            } else {
                st.push(s.charAt(i));
            }
        }

        if (st.empty()) {
            return true;
        }

        return false;
    }

    public static boolean isMatch(char s, char p) {
        if ((s == '(' && p == ')') || (s == '{' && p == '}') || (s == '[' && p == ']')) {
            return true;
        }

        return false;
    }
}
