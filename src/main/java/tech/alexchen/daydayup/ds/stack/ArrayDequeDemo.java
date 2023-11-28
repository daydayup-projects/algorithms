package tech.alexchen.daydayup.ds.stack;

import java.util.ArrayDeque;

/**
 * ArrayDeque 是一个双端队列，它实现了 push 和 pop 的方法，等效于 addFirst 和 removeFirst
 *
 * @author AlexChen
 * @date 2022-07-15 03:28
 */
public class ArrayDequeDemo {

    public static void main(String[] args) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        // 入栈顶
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.add("D");
        System.out.println(stack);

        // 不出栈
        System.out.println(stack.peek());
        // 出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
