package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 技巧：环形链表
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动k个位置，其中k是非负数。
 * <p>
 * 示例1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步:0->1->2->NULL
 * 向右旋转 4 步:2->0->1->NULL
 */
public class T61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int length = 1;
        ListNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
            length++;
        }

        tmp.next = head;
        int moveSize = length - (k % length);
        for (int i = 1; i <= moveSize; i++) {
            if (moveSize == i) {
                ListNode next = head.next;
                head.next = null;
                return next;
            } else {
                head = head.next;
            }
        }
        return head;
    }
}
