package leetcode.linkedlist;

import leetcode.POJO.MultiListNode;

/**
 * 技巧：递归
 *
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * <p>
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 */
public class T430 {

    public static MultiListNode last;
    public static MultiListNode flatten(MultiListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            last = head;
        }
        MultiListNode tmp = head;
        MultiListNode next = tmp.next;
        if (tmp.child != null) {
            MultiListNode childFlatten = flatten(tmp.child);
            tmp.child = null;
            tmp.next = childFlatten;
            childFlatten.prev = tmp;
            tmp = last;
        }
        MultiListNode nextFlatten = flatten(next);
        tmp.next = nextFlatten;
        if (nextFlatten != null) {
            nextFlatten.prev = tmp;
        }
        return head;
    }

    public static void main(String[] args) {
        MultiListNode node = MultiListNode.buildList(1,2,MultiListNode.buildList(3).addChild(MultiListNode.buildList(7, MultiListNode.buildList(8).addChild( MultiListNode.buildList(11,12)),9,10)),4,5,6);
//        MultiListNode node = MultiListNode.buildList(MultiListNode.buildList(1).addChild(3), 2);
        MultiListNode result = flatten(node);
        System.out.println(result);
    }
}
