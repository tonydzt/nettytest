package leetcode.interview;

import leetcode.POJO.ListNode;

/**
 * 见T234
 */
public class D0206 {

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
