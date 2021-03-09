package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 技巧：递归
 *
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class T86 {
    public static ListNode littleEnd;
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode next = partition(head.next, x);
        if (head.val < x) {
            if (littleEnd == null) {
                littleEnd = head;
            }
            head.next = next;
            return head;
        } else {
            if (littleEnd != null) {
                ListNode tmp = littleEnd.next;
                littleEnd.next = head;
                head.next = tmp;
                return next;
            } else{
                head.next = next;
                return head;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(partition(ListNode.buildList(1), 0));
    }
}
