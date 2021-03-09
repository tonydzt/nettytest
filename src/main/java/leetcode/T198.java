package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class T198 {

    private static Map<Integer, Integer> resultMap = new HashMap<>();

    //只能选择跳过一个或者跳过两个，不可能跳过三个（因为三个的中间一个是一定可以拿的），问题就是在某处是否换线
    public static int rob(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        resultMap.put(nums.length - 1, nums[nums.length - 1]);
        resultMap.put(nums.length - 2, Math.max(nums[nums.length - 2], nums[nums.length - 1]));

        return Math.max(rob(nums, 0), rob(nums, 1));
    }

    private static int rob(int[] nums, int startIndex) {

        if (resultMap.get(startIndex) != null) {
            return resultMap.get(startIndex);
        }

        if (startIndex == nums.length - 3) {
            return nums[startIndex] + nums[startIndex + 2];
        }

        Integer oneStep = resultMap.get(startIndex + 2);
        if (oneStep == null) {
            oneStep = rob(nums, startIndex + 2);
            resultMap.put(startIndex + 2, oneStep);
        }

        Integer twoStep = resultMap.get(startIndex + 3);
        if (twoStep == null) {
            twoStep = rob(nums, startIndex + 3);
            resultMap.put(startIndex + 3, twoStep);
        }

        return nums[startIndex] + Math.max(oneStep, twoStep);
    }

    //新建一个数组，记录原数组的计算结果，状态转移方程：dp[i]=max(dp[i-2]+num,dp[i-3]+num)
    // public int rob(int[] nums) {
    //     int[] dp = new int[nums.length + 3];
    //     for (int i = 3; i < dp.length; i++) {
    //         dp[i] = Math.max(dp[i - 2] + nums[i - 3], dp[i - 3] + nums[i - 3]);
    //     }
    //     return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
    // }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }
}
