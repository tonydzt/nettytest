package leetcode;

import java.util.Arrays;

/**
 * 给你一个正整数n ，生成一个包含 1 到n2所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= n <= 20
 *
 * @author douzhitong
 * @date 2021/3/3
 */
public class T59 {

    public static void main(String[] args) {
        int[][] result = new T59().generateMatrix(4);
        for (int[] inner : result) {
            System.out.println(Arrays.toString(inner));
        }
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int horizontal = -1;
        int vertical = 0;
        int total = 0;
        int directIndex = 0;
        boolean isHorizontal = true;
        int boundMax = n;
        int[][] spiral = new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}};
        while (boundMax > 0) {
            int[] direct = spiral[directIndex];
            for (int j = 0; j < boundMax; j++) {
                horizontal += direct[0];
                vertical+= direct[1];
                result[vertical][horizontal] = ++total;
            }
            directIndex++;
            if (directIndex == 4) {
                directIndex = 0;
            }
            isHorizontal = !isHorizontal;
            if (!isHorizontal) {
                boundMax--;
            }
        }
        return result;
    }
}
