package leetcode;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class T96 {

    /**
     * f(n) = f(t1) + f(t2) + f(t3) + ... + f(tn-1)
     * f(n): 整数为n时二叉搜索树的种类数量
     * f(ti): 总数为n, 当i为定点时的二叉搜索树的种类数量
     *
     * f(ti) = f(left) * f(right)
     */
    public static int[] cache;

    public static int numTrees(int n) {
        cache = new int[n+1];
        cache[0] = 1;
        cache[1] = 1;
        return numTreesN(n);
    }

    public static int numTreesN(int n) {

        if (cache[n] != 0) {
            return cache[n];
        }

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += numTreesN(i-1) * numTreesN(n - i);
        }

        cache[n] = total;
        return total;
    }

    public static void main(String[] args) {
        System.out.println(numTrees(1));
        System.out.println(numTrees(2));
        System.out.println(numTrees(3));
        System.out.println(numTrees(4));
        System.out.println(numTrees(5));
    }
}
