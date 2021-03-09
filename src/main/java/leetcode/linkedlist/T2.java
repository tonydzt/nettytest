package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class T2 {

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(ListNode.buildList(2,4,3), ListNode.buildList(5,6,4)));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode origin = new ListNode(0);
        ListNode now = null;
        int trick = 0;
        do {
            if (l1 == null) {
                l1 = new ListNode(0);
            }

            if (l2 == null) {
                l2 = new ListNode(0);
            }

            if (now == null) {
                now = origin;
            } else {
                ListNode newNode = new ListNode(0);
                now.next = newNode;
                now = newNode;
            }

            if (l1.val + l2.val + trick >= 10) {
                now.val = l1.val + l2.val + trick - 10;
                trick = 1;
            } else {
                now.val = l1.val + l2.val + trick;
                trick = 0;
            }

            l1 = l1.next;
            l2 = l2.next;
        } while(l1 != null || l2 !=  null || trick == 1);

        return origin;
    }
}
