package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 给你一个单链表的引用结点head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * 请你返回该链表所表示数字的 十进制值 。
 */
public class T1290 {

    public static int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = (result << 1) + head.val;
            head = head.next;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.buildList(1,0,1);
        System.out.println(getDecimalValue(list));
    }
}
