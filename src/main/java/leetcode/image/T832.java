package leetcode.image;

import java.util.Arrays;

/**
 * 给定一个二进制矩阵A，我们想先水平翻转图像，然后反转图像并返回结果。
 *
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转[1, 1, 0]的结果是[0, 1, 1]。
 *
 * 反转图片的意思是图片中的0全部被1替换，1全部被0替换。例如，反转[0, 1, 1]的结果是[1, 0, 0]。
 *
 * 示例 1：
 *
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 *      然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 示例 2：
 *
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 *      然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * 提示：
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j]<=1
 *
 * @author douzhitong
 * @date 2021/3/3
 */
public class T832 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new T832().flipAndInvertImage(new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 0}})));
    }

    public int[][] flipAndInvertImage(int[][] image) {
        for (int[] ints : image) {
            reverse(ints);
            for (int j = 0; j < ints.length; j++) {
                ints[j] = ints[j] ^ 1;
            }
        }

        return image;
    }

    private void reverse(int[] imageRow) {
        for (int j = 0; j <= Math.ceil(imageRow.length/2.0d) - 1; j++) {
            int thisValue = imageRow[j];
            imageRow[j] = imageRow[imageRow.length - j - 1];
            imageRow[imageRow.length - j - 1] = thisValue;
        }
    }
}
