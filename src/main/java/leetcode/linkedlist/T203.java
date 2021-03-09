package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class T203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode preNode = null;
        ListNode tmpNode = head;
        while(tmpNode != null) {
            if (tmpNode.val == val) {
                if (tmpNode == head) {
                    head = head.next;
                } else {
                    assert preNode != null;
                    preNode.next = tmpNode.next;
                }
            } else {
                preNode = tmpNode;
            }

            tmpNode = tmpNode.next;
        }
        return head;
    }
}
