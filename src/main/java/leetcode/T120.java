package leetcode;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class T120 {

    /**
     * 一行一行累加起来，在最后一行求最小值
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] sum = new int[triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            int[] sumNext = new int[triangle.size()];
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (j == 0) {
                    sumNext[j] = sum[j] + row.get(j);
                } else if (j == row.size() - 1) {
                    sumNext[j] = sum[j - 1] + row.get(j);
                } else {
                    sumNext[j] = Math.min(sum[j - 1], sum[j]) + row.get(j);
                }
            }
            sum = sumNext;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] < min) {
                min = sum[i];
            }
        }

        return min;
    }

    public static void main(String[] args) {
//        List<List<Integer>> triangle = Lists.newArrayList(
//                Lists.newArrayList(2),
//                Lists.newArrayList(3,4),
//                Lists.newArrayList(6,5,7),
//                Lists.newArrayList(4,1,8,3)
//        );
        List<List<Integer>> triangle = Collections.singletonList( Lists.newArrayList(-10));
        System.out.println(minimumTotal(triangle));
    }
}
