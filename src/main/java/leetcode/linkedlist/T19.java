package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */
public class T19 {

    private static int counter = 0;

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEndT(head, n);
    }

    public static ListNode removeNthFromEndT(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        head.next = removeNthFromEndT(head.next, n);
        counter++;
        if (counter == n) {
            return head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(ListNode.buildList(1,2,3,4,5), 2));
    }
}
