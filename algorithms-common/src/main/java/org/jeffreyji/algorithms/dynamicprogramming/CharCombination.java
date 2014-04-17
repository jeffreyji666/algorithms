package org.jeffreyji.algorithms.dynamicprogramming;

/**
 * @author: wgji
 * @date：2014年4月17日 下午7:56:40
 * @comment: 
 * 假设有这样一种字符串，它们的长度不大于 26 ，而且若一个这样的字符串其长度为 m ，则这个字符串必定由 a, b, c ... z 中的前 m
 * 个字母构成，同时我们保证每个字母出现且仅出现一次。比方说某个字符串长度为 5 ，那么它一定是由 a, b, c, d, e 这 5
 * 个字母构成，不会多一个也不会少一个。嗯嗯，这样一来，一旦长度确定，这个字符串中有哪些字母也就确定了，唯一的区别就是这些字母的前后顺序而已。 现在我们用一个由大写字母 A 和 B
 * 构成的序列来描述这类字符串里各个字母的前后顺序： l 如果字母 b 在字母 a 的后面，那么序列的第一个字母就是 A （After），否则序列的第一个字母就是 B （Before）； 
 * l 如果字母 c 在字母 b的后面，那么序列的第二个字母就是 A ，否则就是 B； l 如果字母 d 在字母 c 的后面，那么 …… 不用多说了吧？直到这个字符串的结束。 这规则甚是简单，
 * 不过有个问题就是同一个 AB序列，可能有多个字符串都与之相符，比方说序列"ABA"，就有"acdb"、"cadb"等等好几种可能性。说的专业一点，这一个序列实际上对应了一个字符串集合。
 * 那么现在问题来了：给你一个这样的AB序列，问你究竟有多少个不同的字符串能够与之相符？或者说这个序列对应的字符串集合有多大？注意，只要求个数，不要求枚举所有的字符串。 
 * 注：如果结果大于10亿就返回-1。
 * 
 * 分析：
 * 假设我们现在有输入 s="ABA"， 我们想得到的是各种各样的长度为4的字符串，且字符串的运算结果是"ABA"。 
 * 我们假设一个目标字符串存储在char array[4]。先考虑字符'a'放在哪儿， 
 * 我们假设随便选一个位置i，那么array[4]的区间[0,3]分成了两个区间[0,i-1]和[i+1,3]。 
 * 然后考虑‘b’放在哪儿，需要根据"ABA"的第一个字符"A"，就是说‘b'应该在'a'之后， 也就是'b'应该在区间[i+1,3]里面， 
 * 假设我们选位置j，那么我们现在有区间：[0,i-1], [i+1,j-1] 和[j+1,3]。 接着考虑字符'c'的位置， 
 * 因为'c'只和'b'有关系，而且'b'在j， 所以前面两个区间貌似可以合并，怎么合并？ 
 * 其实我们只需要用区间的长度代表区间就行了， 也就是说前两个区间的合并就是两个长度的合并。
 * 所以设计一个动态程序， 状态是 memo[left][right]， left表示左边区间的长度，right表示右边区间的长度， 
 * 两者之和可以确定当前应该考虑哪个字符了。 复杂度是O(n^3) 还没想到如何搞到O(n^2)
 */
public class CharCombination {
    public static void main(String[] args) {
        System.out.print(solve("AB"));
    }

    private static String charArray;
    private static int[][] memo;
    private static boolean[][] vst;

    public static int solve(String s) {
        charArray = s;
        memo = new int[charArray.length() + 1][charArray.length() + 1];
        vst = new boolean[charArray.length() + 1][charArray.length() + 1];
        int ret = 0;
        for (int i = 0; i < charArray.length() + 1; i++) {
            ret += dp(i, charArray.length() - i);
        }
        return ret;
    }

    public static int dp(int left, int right) {
        if (left + right == 0) {
            return 1;
        }
        if (vst[left][right]) {
            return memo[left][right];
        }
        vst[left][right] = true;
        int ret = 0;
        int index = charArray.length() - left - right;
        if (charArray.charAt(index) == 'A') {
            for (int i = 0; i < right; i++) {
                ret += dp(left + i, right - i - 1);
            }
        } else {
            for (int i = 0; i < left; i++) {
                ret += dp(left - i - 1, right + i);
            }
        }
        return memo[left][right] = ret;
    }
}
