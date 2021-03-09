package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 */
public class T63 {

    private static Map<String, Integer> cache = new HashMap<>();

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        cache.put("0_0", obstacleGrid[0][0] == 1 ? 0 : 1);

        for (int i = 1; i < obstacleGrid.length; i++) {

            String keyPre = (i - 1) + "_" + 0;
            String key = i + "_" + 0;
            if (obstacleGrid[i][0] == 0 && cache.get(keyPre) == 1) {
                cache.put(key, 1);
            } else {
                cache.put(key, 0);
            }
        }

        for (int j = 1; j < obstacleGrid[0].length; j++) {

            String keyPre = 0 + "_" + (j - 1);
            String key = 0 + "_" + j;
            if (obstacleGrid[0][j] == 0 && cache.get(keyPre) == 1) {
                cache.put(key, 1);
            } else {
                cache.put(key, 0);
            }
        }

        return getFromCache(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    //状态转移方程 u(m,n) = u(m-1,n) + u(m,n-1)
    private static int uniquePathsWithObstacles(int[][] obstacleGrid, int m, int n) {
        if (obstacleGrid[m][n] == 1) {
            return 0;
        }

        return getFromCache(obstacleGrid, m - 1, n) + getFromCache(obstacleGrid, m, n - 1);
    }

    private static int getFromCache(int[][] obstacleGrid, int m, int n) {

        String key = m + "_" + n;
        Integer num = cache.get(key);
        if (num == null) {
            num = uniquePathsWithObstacles(obstacleGrid, m, n);
            cache.put(key, num);
        }

        return num;
    }

    public static void main(String[] args) {
//        int[][] a = new int[][]{
//                {0,0,0},
//                {0,1,0},
//                {0,0,0}
//        };
//        int[][] a = new int[][]{
//                {0}
//        };
//        int[][] a = new int[][]{
//                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
//        };
//        int[][] a = new int[][]{
//                {1}
//        };
        int[][] a = new int[][]{
                {0, 0},
                {0, 0},
        };
        System.out.println(uniquePathsWithObstacles(a));
    }
}
