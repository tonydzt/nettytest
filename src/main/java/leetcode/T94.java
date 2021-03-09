package leetcode;

import leetcode.POJO.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 *
 * 输出: [1,3,2]
 */
public class T94 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(inorderTraversal(root));
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            if (root.left != null) {
                list.addAll(inorderTraversal(root.left));
            }
            list.add(root.val);
            if (root.right != null) {
                list.addAll(inorderTraversal(root.right));
            }
        }
        return list;
    }
}
