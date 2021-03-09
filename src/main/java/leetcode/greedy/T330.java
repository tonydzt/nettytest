package leetcode.greedy;

/**
 * 给定一个已排序的正整数数组 nums，和一个正整数n 。从[1, n]区间内选取任意个数字补充到nums中，使得[1, n]区间内的任何数字都可以用nums中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
 *
 * 示例:
 *
 * 输入: nums = [1,3], n = 6
 * 输出: 1 
 * 解释:
 * 根据 nums里现有的组合[1], [3], [1,3]，可以得出1, 3, 4。
 * 现在如果我们将2添加到nums 中，组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 * 其和可以表示数字1, 2, 3, 4, 5, 6，能够覆盖[1, 6]区间里所有的数。
 * 所以我们最少需要添加一个数字。
 * 示例 2:
 *
 * 输入: nums = [1,5,10], n = 20
 * 输出: 2
 * 解释: 我们需要添加[2, 4]。
 * 示例3:
 *
 * 输入: nums = [1,2,2], n = 5
 * 输出: 0
 *
 * @author douzhitong
 * @date 2021/3/1
 */
public class T330 {

    public static void main(String[] args) {
//        System.out.println(new T330().minPatches(new int[]{2,9,9,21,30,38,39,52,53,55,67,72,77,78,85,89,89,97}, 36));
//        System.out.println(new T330().minPatches(new int[]{1,5,10}, 22));
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(new T330().minPatches(new int[]{1,2,31,33}, 2147483647));
//        System.out.println(2147483647 - 1610612736);
    }

    public int minPatches(int[] nums, int n) {
//        int minPaches = 0;
//        int index = 0;
//        int total = 0;
//        for (int i = 1; i <= n; i++) {
//            if (index == nums.length || total >= n) {
//                break;
//            }
//            if (nums[index] == i) {
//                total+=nums[index];
//                index++;
//                i--;
//            } else if (total < i) {
//                total+=i;
//                minPaches++;
//            }
//        }
//        while (total < n) {
//            if (Integer.MAX_VALUE - total < total) {
//                minPaches++;
//                break;
//            }
//            total=total*2+1;
//            minPaches++;
//        }
//        return minPaches;
        // 记录需要填充新的x的个数, 即结果
        int res = 0;
        // 当前最小未被覆盖的整数
        long x = 1;
        int length = nums.length;
        // 遍历的数组nums下标
        int index = 0;
        while (x <= n)
        {
            if (index < length && nums[index] <= x)
            {
                // 增加未被覆盖的范围
                x += nums[index];
                // 遍历下一个
                index++;
            }
            else
            {
                // 填充一个x进去，此时x可以扩大一倍
                x <<= 1;
                // 因为填充一个新的x，那么自然计数+1
                ++res;
            }
        }
        return res;
    }


}
