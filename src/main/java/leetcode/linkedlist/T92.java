package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤m≤n≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class T92 {

    private static ListNode reverseEnd;

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }

        ListNode nextReverse = reverseBetween(head.next, --m, --n);
        if (m <= 0 && n >= 0) {
            if (reverseEnd == null) {
                reverseEnd = head;
                head.next = nextReverse;
            } else {
                ListNode tmp = reverseEnd.next;
                reverseEnd.next = head;
                head.next = tmp;
                reverseEnd = head;
                return nextReverse;
            }
        } else {
            head.next = nextReverse;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(reverseBetween(ListNode.buildList(1,2,3,4,5), 2, 4));
    }
}
