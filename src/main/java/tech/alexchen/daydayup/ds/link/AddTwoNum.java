package tech.alexchen.daydayup.ds.link;

/**
 * @author AlexChen
 * @date 2022-07-14 19:29
 */
public class AddTwoNum {

    public static void main(String[] args) {
        AddTwoNum addTwoNum = new AddTwoNum();
        addTwoNum.test();
    }

    public void test() {
        // 123
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n3.next = n2;
        n2.next = n1;

        // 456
        ListNode m1 = new ListNode(4);
        ListNode m2 = new ListNode(5);
        ListNode m3 = new ListNode(6);
        m3.next = m2;
        m2.next = m1;

        // 123+456=579 返回 9 7 5
        ListNode listNode = addTwoNumbers(n3, m3);
        while (listNode != null) {
            System.out.printf("%d ", listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
