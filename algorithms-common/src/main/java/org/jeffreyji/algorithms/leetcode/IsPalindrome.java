package org.jeffreyji.algorithms.leetcode;

/**
 * @author: wgji
 * @date：2014年5月25日 下午12:34:43
 * @comment:Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */

public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("aA"));
    }

    public static boolean isPalindrome(String s) {
        assert (s != null && s.length() > 0);
        
        StringBuilder  sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
            if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}
