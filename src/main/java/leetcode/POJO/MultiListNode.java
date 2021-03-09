package leetcode.POJO;

/**
 * @author douzhitong
 * @date 2020/12/3
 */
public class MultiListNode {
    public int val;
    public MultiListNode prev;
    public MultiListNode next;
    public MultiListNode child;

    public MultiListNode(int val) {
        this.val = val;
    }

    public static MultiListNode buildList(Object ... vals) {
        MultiListNode result = null;
        MultiListNode returnResult = null;
        for (Object val : vals) {
            if (val instanceof Integer) {
                val = new MultiListNode((Integer) val);
            }
            if (result == null) {
                result = (MultiListNode) val;
                returnResult = result;
            } else {
                result.addNext((MultiListNode) val);
            }
            result = (MultiListNode) val;
        }
        return returnResult;
    }

    public MultiListNode addPre(MultiListNode val) {
        this.prev = val;
        if (val != null) {
            val.next = this;
        }
        return this;
    }

    public MultiListNode addNext(MultiListNode val) {
        this.next = val;
        if (val != null) {
            val.prev = this;
        }
        return this;
    }

    public MultiListNode addChild(Object val) {
        if (val instanceof Integer) {
            val = new MultiListNode((Integer) val);
        }
        this.child = (MultiListNode) val;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        MultiListNode nowNode = this;
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
        MultiListNode node = MultiListNode.buildList(1,2,MultiListNode.buildList(3).addChild(MultiListNode.buildList(7, MultiListNode.buildList(8).addChild( MultiListNode.buildList(11,12)),9,10)),4,5,6);
        System.out.println(node);
    }
}
