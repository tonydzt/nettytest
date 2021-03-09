package leetcode.tree;

import leetcode.POJO.ListNode;
import leetcode.POJO.TreeNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * @author douzhitong
 * @date 2021/3/3
 */
public class T109 {

    public static void main(String[] args) {
        System.out.println(new T109().sortedListToBST(ListNode.buildList(-10, -3, 0, 5, 9)));
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode thisNode = new TreeNode(slow.val);
        if (pre == null) {
            thisNode.left = null;
        } else {
            pre.next = null;
            thisNode.left = sortedListToBST(head);
        }

        thisNode.right = slow.next == null ? null : sortedListToBST(slow.next);
        return thisNode;
    }
}
