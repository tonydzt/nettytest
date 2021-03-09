package leetcode.tree;

import leetcode.POJO.TreeNode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * @author douzhitong
 * @date 2021/3/3
 */
public class T124 {

    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1);
        TreeNode left = new TreeNode(-2);
        TreeNode right = new TreeNode(-3);
        root.left = left;
        root.right = right;
        System.out.println(new T124().maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        int pathSum = pathSum(root, true);
        return Math.max(pathSum, max);
    }

    private int pathSum(TreeNode root, boolean isRoot) {
        if (root == null) {
            return 0;
        }
        int leftMaxPathSum = pathSum(root.left, false);
        int rightMaxPathSum = pathSum(root.right, false);
        int maxChild = Math.max(leftMaxPathSum, rightMaxPathSum);
        int thisVal = maxChild > 0 ? root.val + maxChild : root.val;
        int thisSum = leftMaxPathSum + rightMaxPathSum + root.val;
        if (thisSum > max) {
            max = thisSum;
        }
        return isRoot ? thisVal : Math.max(thisVal, 0);
    }
}
