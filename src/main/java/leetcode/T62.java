package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class T62 {

    private static Map<String,Integer> cache = new HashMap<>();

    //状态转移方程 u(m,n) = u(m-1,n) + u(m,n-1)
    public static int uniquePaths(int m, int n) {

        if (m == 1) {
            return 1;
        }

        if (n == 1) {
            return 1;
        }

        return getFromCache(m - 1, n) + getFromCache(m, n - 1);
    }

    private static int getFromCache(int m, int n) {

        String key = m > n ? m + "_" + n : n + "_" + m;
        Integer num = cache.get(key);
        if (num == null) {
            num = uniquePaths(m, n);
            cache.put(key, num);
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(70,30));
    }
}
