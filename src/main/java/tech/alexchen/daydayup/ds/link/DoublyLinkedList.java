package tech.alexchen.daydayup.ds.link;

/**
 * 双向链表（使用伪头节点和伪尾节点）
 *
 * @author AlexChen
 * @date 2022-07-15 02:53
 */
public class DoublyLinkedList {

    Node head;
    Node tail;
    int size;

    public DoublyLinkedList() {
        size = 0;
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int index) {
        // 0 ~ size-1
        if (index < 0 || index >= size) {
            return -1;
        }
        Node cur = head;
        // 判断离哪边近，则从哪边开始遍历
        if (index < (size >> 1)) {
            //  从头遍历，遍历 index+1 次
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = size-1; i >= index; i--) {
                cur = cur.pre;
            }
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        Node oldNode = head.next;
        Node newNode = new Node(head, val, head.next);
        head.next = newNode;
        oldNode.pre = newNode;
        size++;
    }

    public void addAtTail(int val) {
        Node oldNode = tail.pre;
        Node newNode = new Node(oldNode, val, tail);
        tail.pre = newNode;
        oldNode.next = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        // 0 ~ size
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        // 找到该位置的节点
        Node cur = head;
        // 判断离哪边近，则从哪边开始遍历
        if (index < (size >> 1)) {
            //  从头遍历，遍历 index+1 次
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = size-1; i >= index; i--) {
                cur = cur.pre;
            }
        }
        Node pre = cur.pre;
        Node newNode = new Node(pre, val, cur);
        pre.next = newNode;
        cur.pre = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        // 找到该位置的节点
        Node cur = head;
        // 判断离哪边近，则从哪边开始遍历
        if (index < (size >> 1)) {
            //  从头遍历，遍历 index+1 次
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = size-1; i >= index; i--) {
                cur = cur.pre;
            }
        }
        Node pre = cur.pre;
        pre.next = cur.next;
        cur.next.pre = pre;
        size--;
    }

    private class Node {
        int val;
        Node pre;
        Node next;

        public Node(){}

        public Node(int val) {
            this.val = val;
        }
        public Node(Node pre, int val, Node next) {
            this.pre = pre;
            this.val = val;
            this.next = next;
        }
    }
}
