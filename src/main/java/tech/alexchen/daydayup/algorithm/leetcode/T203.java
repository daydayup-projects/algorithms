package tech.alexchen.daydayup.algorithm.leetcode;

/**
 * @author alexchen
 */
public class T203 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        removeElements(node1, 6);
    }

    public static ListNode removeElements(ListNode head, int val) {
        // 虚拟头结点
        ListNode node = new ListNode(0);
        // 虚拟头结点指向 head
        node.next = head;
        // 滑动结点，如果用 node 的话，无法返回结果
        ListNode prev = node;
        while (prev.next != null) {
            // 删除
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                // 不删除
                prev = prev.next;
            }
        }
        return node.next;
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
