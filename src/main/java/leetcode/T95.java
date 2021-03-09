package leetcode;

import leetcode.POJO.TreeNode;
import java.util.*;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class T95 {

    private static Map<String, List<TreeNode>> cache = new HashMap<>();

    /**
     * 状态转移方程 tree(start, end) = top(start) + top(start + 1) + top(start + 2) + ... + top(end）
     * top(i) 代表以i为顶点的树
     */
    public static List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int start, int end) {
        String key = start + "," + end;
        if (cache.get(key) != null) {
            return cache.get(key);
        }

        List<TreeNode> treeNodeList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = i > start ? generateTrees(start, i - 1) : Collections.singletonList(null);
            List<TreeNode> right = i < end ? generateTrees(i + 1, end) : Collections.singletonList(null);

            for (TreeNode treeNodeLeft : left) {
                for (TreeNode treeNodeRight : right) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNodeList.add(treeNode);
                    treeNode.left = treeNodeLeft;
                    treeNode.right = treeNodeRight;
                }
            }
        }

        cache.put(key, treeNodeList);
        return treeNodeList;
    }

    public static void main(String[] args) {
        System.out.println(generateTrees(3).size());
    }
}
