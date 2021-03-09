package leetcode;

import leetcode.POJO.TreeNode;

/**
 * 验证二叉搜索树
 * 分类：深度优先搜索
 * 相关：
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 *
 * 执行用时 :2 ms, 在所有 Java 提交中击败了58.97%的用户
 * 内存消耗 :38.8 MB, 在所有 Java 提交中击败了30.73%的用户
 */
public class T98 {

    public static boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        if (root.left != null) {
            //优化1
//            if (root.val <= root.left.val) {
//                return false;
//            }
            if (!isValidBST(root.left)) {
                return false;
            }
            if (root.val <= getMostRightVal(root.left)) {
                return false;

            }
        }
        if (root.right != null) {
            //优化1
//            if (root.val >= root.right.val) {
//                return false;
//            }
            if (!isValidBST(root.right)) {
                return false;
            }
            if (root.val >= getMostLeft(root.right)) {
                return false;
            }
        }

        return true;
    }

    private static int getMostLeft(TreeNode root) {
        if (root.left != null) {
            return getMostLeft(root.left);
        }

        return root.val;
    }

    private static int getMostRightVal(TreeNode root) {

        if (root.right != null) {
            return getMostRightVal(root.right);
        }

        return root.val;
    }

    public static void main(String[] args) {
        //[2,1,3]
//        TreeNode treeNode = new TreeNode(2);
//        treeNode.left = new TreeNode(1);
//        treeNode.right = new TreeNode(3);
//        System.out.println(isValidBST(treeNode));

        //[10,5,15,null,null,6,20]
        TreeNode treeNode1 = new TreeNode(10);
        treeNode1.left = new TreeNode(5);
        treeNode1.right = new TreeNode(15);
        treeNode1.right.left = new TreeNode(6);
        treeNode1.right.right = new TreeNode(20);
        System.out.println(isValidBST(treeNode1));
    }
}
