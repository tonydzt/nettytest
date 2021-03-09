package leetcode.linkedlist;

import leetcode.POJO.ListNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class T23 {

    private Set<Integer> slots;

    /**
     * 一个一个递归
     * 很慢
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        return mergeKLists(lists, 0);
    }

    public ListNode mergeKLists(ListNode[] lists, int index) {
        if (lists == null || lists.length == 0 || lists.length == index) {
            return null;
        }
        Set<Integer> a = new HashSet<>(2);
        a.add(2);
        Iterator<Integer> b = a.iterator();
        while (b.hasNext()) {

        }
        ListNode mergeList = mergeKLists(lists, ++index);
        if (mergeList == null) {
            return lists[index - 1];
        } else {
            return ListNode.merge(lists[index - 1], mergeList);
        }
    }


    /**
     * 全部一起递归，更加慢。
     * 很慢
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (slots == null) {
            slots = new HashSet<>(lists.length);
            for (int i = 0; i < lists.length; i++) {
                slots.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        Iterator<Integer> iterator = slots.iterator();
        while (iterator.hasNext()) {
            int index = iterator.next();
            ListNode node = lists[index];
            if (node == null) {
                iterator.remove();
            } else {
                if (node.val < min) {
                    min = node.val;
                    minIndex = index;
                }
            }
        }

        if (min == Integer.MAX_VALUE) {
            return null;
        }

        ListNode node = lists[minIndex];
        lists[minIndex] = node.next;
        node.next = mergeKLists2(lists);
        return node;
    }
}
