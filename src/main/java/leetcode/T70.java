package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class T70 {

    static Map<Integer, Integer> resultMap = new HashMap<>();

    // 找规律，其实是一个斐波那契数列，动态规划减少时间复杂度
    public static int climbStairs(int n) {

        resultMap.put(1,1);
        resultMap.put(2,2);
        return climbStairsFF(n);
    }

    private static int climbStairsFF(int n) {
        if (resultMap.get(n) != null) {
            return resultMap.get(n);
        }

        int result = climbStairsFF(n - 2) + climbStairsFF(n - 1);
        resultMap.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(20));
    }
}
