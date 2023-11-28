package tech.alexchen.daydayup.ds.stack;

import java.util.Stack;

/**
 * @author AlexChen
 * @date 2022-07-15 03:26
 */
public class StackDemo {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        System.out.println(stack);

        // 返回对象在此堆栈上的从 1 开始的位置，-1 表示未找到
        System.out.println("C 在此堆栈上的从 1 开始的位置:" + stack.search("C"));
        System.out.println("D 在此堆栈上的从 1 开始的位置:" + stack.search("D"));

        // 不出栈
        System.out.println(stack.peek());
        // 出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
