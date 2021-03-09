package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 技巧：补零对齐、递归
 *
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class T445 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        while (tmp1 != null || tmp2 != null) {
            if (tmp1 == null) {
                ListNode node = new ListNode(0);
                node.next = l1;
                l1 = node;
                tmp2 = tmp2.next;
                continue;
            } else if (tmp2 == null) {
                ListNode node = new ListNode(0);
                node.next = l2;
                l2 = node;
                tmp1 = tmp1.next;
                continue;
            }
            tmp1 = tmp1.next;
            tmp2 = tmp2.next;
        }

        return addTwoNumbersO(l1, l2, true);
    }

    public static ListNode addTwoNumbersO(ListNode l1, ListNode l2, boolean isOut) {

        if (l1 == null) {
            return new ListNode(0);
        }

        ListNode next = addTwoNumbersO(l1.next, l2.next, false);
        int sum = l1.val + l2.val + next.val;
        if (sum >= 10) {
            next.val = sum - 10;
            ListNode node = new ListNode(1);
            node.next = next;
            return node;
        } else {
            next.val = sum;
            if (!isOut) {
                ListNode node = new ListNode(0);
                node.next = next;
                return node;
            }
            return next;
        }
    }

    public static void main(String[] args) {
        System.out.println(addTwoNumbers(ListNode.buildList(7,2,4,3), ListNode.buildList(5,6,4)));
    }
}
