package tech.alexchen.daydayup.ds.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author alexchen
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);

        // 注意队列和栈方法的区别
        stack.offer(6);
        System.out.println(stack.poll());
        System.out.println(stack);
    }
}
