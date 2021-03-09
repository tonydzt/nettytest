package leetcode.POJO;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author douzhitong
 * @date 2019/10/14
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode sort(ListNode listNode) {
        return null;
    }

    public static ListNode reverse(ListNode listNode, AtomicInteger counter) {
        // 指针翻转
        ListNode pre = null;
        ListNode current = listNode;
        while (current != null) {
            ListNode tmp = current;
            current = current.next;
            tmp.next = pre;
            pre = tmp;
            if (counter != null) {
                counter.getAndAdd(1);
            }
        }

        return pre;
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        Ref<ListNode> reverseEnd = new Ref<>(null);
        return reverseBetween(head, m, n, reverseEnd);
    }

    private static ListNode reverseBetween(ListNode head, int m, int n, Ref<ListNode> reverseEnd) {
        if (head == null) {
            return head;
        }
        ListNode nextReverse = reverseBetween(head.next, --m, --n, reverseEnd);
        if (m <= 0 && n >= 0) {
            if (reverseEnd.get() == null) {
                reverseEnd.set(head);
                head.next = nextReverse;
            } else {
                ListNode tmp = reverseEnd.get().next;
                reverseEnd.get().next = head;
                head.next = tmp;
                reverseEnd.set(head);
                return nextReverse;
            }
        } else {
            head.next = nextReverse;
        }
        return head;
    }

    public static ListNode buildList(ListNode inter, int ... nums) {

        if (nums == null || nums.length == 0) {
            return null;
        }

        ListNode origin = null;
        ListNode first = null;

        for (int num : nums) {
            if (first == null) {
                origin = new ListNode(num);
                first = origin;
                continue;
            }

            ListNode newNode = new ListNode(num);
            first.next = newNode;
            first= newNode;
        }
        first.next = inter;
        return origin;
    }

    public static ListNode buildList(int ... nums) {

        if (nums == null || nums.length == 0) {
            return null;
        }

        ListNode origin = null;
        ListNode first = null;

        for (int num : nums) {
            if (first == null) {
                origin = new ListNode(num);
                first = origin;
                continue;
            }

            ListNode newNode = new ListNode(num);
            first.next = newNode;
            first= newNode;
        }

        return origin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode nowNode = this;
        do {
            sb.append(nowNode.val);
            sb.append(" -> ");
            nowNode = nowNode.next;
        } while (nowNode != null);

        if (sb.length() > 0) {
            sb.delete(sb.lastIndexOf(" -> "), sb.length());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(reverse(buildList(1,2,3,4), null));
        System.out.println(reverseBetween(buildList(1,2,3,4,5), 2,4));
        System.out.println(reverseBetween(buildList(3,5), 1,2));
        System.out.println(merge(buildList(1,2,3,5), buildList(1,1,2,3,3,4)));
    }
}
