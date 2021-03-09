package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class T152 {

    private static List<Integer> zeroIndexList = new ArrayList<>();
    private static List<Integer> negativeIndexList = new ArrayList<>();

    public static int maxProduct(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIndexList.add(i);
            }
            if (nums[i] < 0) {
                negativeIndexList.add(i);
            }
        }

        if (zeroIndexList.size() == 0) {
            return maxProductNoZero(nums, 0, nums.length - 1);
        } else {
            int max = 0;
            for (int i = 0; i < zeroIndexList.size(); i++) {
                if (zeroIndexList.get(i) != 0 && zeroIndexList.get(i) != nums.length - 1) {
                    int result = maxProductNoZero(nums, i == 0 ? 0 : zeroIndexList.get(i-1) + 1, i == zeroIndexList.size() - 1 ? nums.length - 1 : zeroIndexList.get(i) - 1);
                    if (result > max) {
                        max = result;
                    }
                }
            }
            return max;
        }
    }

    public static int maxProductNoZero(int[] nums, int start, int end) {

        List<Integer> negativeIndexListLocal = new ArrayList<>();
        for (Integer negativeIndex : negativeIndexList) {
            if (negativeIndex >= start && negativeIndex <= end) {
                negativeIndexListLocal.add(negativeIndex);
            }
        }

        if (negativeIndexListLocal.size() % 2 == 0) {
            int total = 1;
            for (int i = start; i <= end; i++) {
                total *= nums[i];
            }
            return total;
        } else {
            if (start == end) {
                return nums[start];
            }
            int totalLeft = 1;
            int totalRight = 1;
            for (int i = start; i <= negativeIndexListLocal.get(negativeIndexListLocal.size() - 1) - 1; i++) {
                totalLeft *= nums[i];
            }
            for (int i = negativeIndexListLocal.get(0) + 1; i <= end; i++) {
                totalRight *= nums[i];
            }
            return Math.max(totalLeft, totalRight);
        }
    }

    public static void main(String[] args) {
//        System.out.println(maxProduct(new int[]{2,3,-2,4}));
//        System.out.println(maxProduct(new int[]{-2,0,-1}));
//        System.out.println(maxProduct(new int[]{-2}));
        System.out.println(maxProduct(new int[]{0,2}));
    }
}
