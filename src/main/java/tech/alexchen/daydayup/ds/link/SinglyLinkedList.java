package tech.alexchen.daydayup.ds.link;

/**
 * 单向链表（使用伪头节点）
 *
 * @author AlexChen
 * @date 2022-07-15 01:26
 */
class SinglyLinkedList {

    Node head;
    int size;

    public SinglyLinkedList() {
        head = new Node(0);
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node temp = head;
        // 从 head 开始遍历，遍历 index+1 次，当 index 为0 时，遍历一次，正好获取 head 指向的第一个元素
        for(int i=0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        Node pre = head;
        // 找到前一个节点，遍历 index 次，index 为0时，正好获取 head 节点
        for(int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node newNode = new Node(val, pre.next);
        pre.next = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node pre = head;
        // 找到前一个节点，遍历 index 次，index 为0时，正好获取 head 节点
        for(int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        size--;
    }

    private class Node {
        int val;
        Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}


