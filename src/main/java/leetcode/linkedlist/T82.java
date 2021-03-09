package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 技巧：递归
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * <p>
 * 示例1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class T82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        int a = head.val;
        int counter = 1;
        ListNode tmp = head;
        while (tmp.next != null && tmp.next.val == a) {
            tmp = tmp.next;
            counter++;
        }
        ListNode next = deleteDuplicates(tmp.next);
        if (counter > 1) {
            return next;
        } else {
            tmp.next = next;
            return head;
        }
    }
}
