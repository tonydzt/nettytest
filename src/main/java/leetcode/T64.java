package leetcode;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class T64 {

    public static int[][] cache;

    //状态转移方程 u(m,n) = num(m,n) + max(u(m-1,n), u(m,n-1))
    public static int minPathSum(int[][] grid) {
        cache = new int[grid.length][grid[0].length];
        return getFromCache(grid, grid.length - 1, grid[0].length - 1);
    }

    private static int getFromCache(int[][] grid, int m, int n) {
        if (cache[m][n] > 0) {
            return cache[m][n];
        }

        int result = minPathSum(grid, m, n);
        cache[m][n] = result;
        return result;
    }

    private static int minPathSum(int[][] grid, int m, int n) {
        if (m == 0) {
            int total = 0;
            while(n >= 0) {
                total += grid[0][n];
                n--;
            }
            return total;
        }
        if (n == 0) {
            int total = 0;
            while(m >= 0) {
                total += grid[m][0];
                m--;
            }
            return total;
        }

        return grid[m][n] + Math.min(getFromCache(grid, m-1, n), getFromCache(grid, m, n-1));
    }



    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum(a));
    }
}
