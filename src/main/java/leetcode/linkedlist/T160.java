package leetcode.linkedlist;

import leetcode.POJO.ListNode;

/**
 * 技巧：链表成环
 *
 * @author douzhitong
 * @date 2020/12/2
 */
public class T160 {

    public static void main(String[] args) {
        ListNode inter = ListNode.buildList(3,4,5);
        ListNode a = ListNode.buildList(inter,1,2);
        ListNode b = ListNode.buildList(inter,7,8);
        System.out.println(getIntersectionNode(a, b));
    }

    // 走一下彼此的路
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 即使A、B不相交，也会在第二圈终止的时候A=B=null停下，因为这时走过的路程正好一样了
        ListNode pA = headA, pB = headB;
        while(pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    // 翻转三次
//    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        AtomicInteger aLength = new AtomicInteger(0);
//        ListNode endA = ListNode.reverse(headA, aLength);
//
//        AtomicInteger bLength = new AtomicInteger(0);
//        ListNode endB = ListNode.reverse(headB, bLength);
//
//        if (headA != endB) {
//            ListNode.reverse(endA, aLength);
//            ListNode.reverse(endB, aLength);
//            return null;
//        }
//        AtomicInteger cLength = new AtomicInteger(0);
//        ListNode.reverse(endA, cLength);
//
//        int aToMiddle = (aLength.get() + bLength.get() + cLength.get()) / 2 - cLength.get();
//        ListNode result = headA;
//        for (int i = 0; i < aToMiddle; i++) {
//            result = result.next;
//        }
//        return result;
//    }
}
