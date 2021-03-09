package leetcode.math;

/**
 * 思路：平面向量求三角形面积公式 |x1y2 - x2y1| / 2
 *
 * 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 *
 * 示例:
 * 输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * 输出: 2
 * 解释: 
 * 这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
 *
 *
 * 注意:
 *
 * 3 <= points.length <= 50.
 * 不存在重复的点。
 * -50 <= points[i][j] <= 50.
 * 结果误差值在10^-6以内都认为是正确答案。
 *
 * @author douzhitong
 * @date 2021/3/3
 */
public class T812 {

    public static void main(String[] args) {
        System.out.println(new T812().largestTriangleArea(new int[][]{{0,0},{0,1},{1,0},{0,2},{2,0}}));
    }

    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for (int x = 0; x < points.length - 2; x++) {
            for (int y = x + 1; y < points.length - 1; y++) {
                for (int z = y + 1; z < points.length; z++) {
                    double area = calculateArea(new int[][]{points[x], points[y], points[z]});
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }

    private double calculateArea(int[][] points) {
        int[] bound1 = new int[]{points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] bound2 = new int[]{points[1][0] - points[2][0], points[1][1] - points[2][1]};
        // 如果三点共线x1y1-x2y1 = 0
        return Math.abs(bound1[0] * bound2[1] - bound1[1] * bound2[0]) / 2.0d;
    }
}
