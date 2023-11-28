package tech.alexchen.daydayup.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author alexchen
 */
public class T1047 {

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }

    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || (stack.peek() != ch)) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
