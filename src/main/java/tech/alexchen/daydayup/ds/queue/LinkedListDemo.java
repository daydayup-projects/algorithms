package tech.alexchen.daydayup.ds.queue;

import cn.hutool.core.util.StrUtil;

import java.util.LinkedList;

/**
 * @author AlexChen
 * @date 2022-07-14 00:02
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        queueTest();
//        stackTest();
    }

    public static void queueTest() {
        LinkedList<String> queue = new LinkedList<>();
        // 入队尾，本质都是调用 linkLast(E e) 方法
        queue.offer("A");
        queue.add("B");
        queue.addLast("C");
        queue.offerLast("D");
        queue.offer("E");
        queue.offer("F");
        queue.offer("G");
        System.out.println("queue: " + queue);

        // 检索但不出队，本质是返回头结点或尾结点的 item 属性，空时会返回 null
        String peek = queue.peek();
        System.out.println(StrUtil.format("Peek:{} >>> queue:{}", peek, queue));
        peek = queue.peekFirst();
        System.out.println(StrUtil.format("peekFirst:{} >>> queue:{}", peek, queue));
        peek = queue.peekLast();
        System.out.println(StrUtil.format("peekLast:{} >>> queue:{}", peek, queue));

        // 空时会抛异常 NoSuchElementException
        peek = queue.element();
        System.out.println(StrUtil.format("element:{} >>> queue:{}", peek, queue));
        peek = queue.getFirst();
        System.out.println(StrUtil.format("getFirst:{} >>> queue:{}", peek, queue));
        peek = queue.getLast();
        System.out.println(StrUtil.format("getLast:{} >>> queue:{}", peek, queue));
        System.out.println();

        // 先进先出，从队头出队列，本质都是调用 unlinkFirst(E e) 方法，空时会返回 null
        String poll = queue.poll();
        System.out.println(StrUtil.format("poll:{} >>> queue:{}", poll, queue));
        poll = queue.remove();
        System.out.println(StrUtil.format("remove:{} >>> queue:{}", poll, queue));
        // 空时会抛异常 NoSuchElementException
        poll = queue.removeFirst();
        System.out.println(StrUtil.format("removeFirst:{} >>> queue:{}", poll, queue));
        System.out.println();

        // 插入队头
        queue.addFirst("H");
        System.out.println("addFirst:H >>> queue:" + queue);
        queue.offerFirst("i");
        System.out.println("offerFirst:i >>> queue:" + queue);
    }

    public static void stackTest() {
        LinkedList<String> stack = new LinkedList();

    }

}
