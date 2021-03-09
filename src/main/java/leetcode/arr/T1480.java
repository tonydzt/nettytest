package leetcode.arr;

import java.util.Arrays;

/**
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 *
 * 请返回 nums 的动态和。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,6,10]
 * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：[1,2,3,4,5]
 * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
 * 示例 3：
 *
 * 输入：nums = [3,1,2,10,1]
 * 输出：[3,4,6,16,17]
 * 
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -10^6<= nums[i] <=10^6
 *
 * @author douzhitong
 * @date 2021/3/9
 */
public class T1480 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new T1480().runningSum(new int[]{1,2,3,4})));
    }

    public int[] runningSum(int[] nums) {
        int[] sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sums[i] = nums[i] + (i == 0 ? 0 : sums[i-1]);
        }

        return sums;
    }
}
