package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 技巧：快慢指针
 * Link：T206，T876
 *
 * 编写一个函数，检查输入的链表是否是回文的。
 * 示例 1：
 * <p>
 * 输入： 1->2
 * 输出： false
 * 示例 2：
 * <p>
 * 输入： 1->2->2->1
 * 输出： true
 * <p>
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class T234 {

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reverse;
        ListNode pre = null;
        while (slow != null) {
            ListNode tmp = slow;
            slow = slow.next;
            tmp.next = pre;
            pre = tmp;
        }
        reverse = pre;

        while (reverse != null) {
            if (reverse.val != head.val) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }
        return true;
    }
}
