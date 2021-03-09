package leetcode.greedy;

/**
 * 思路：找出环形最大子集，最大子集的第一个index就是起始点（如果总大小>0）
 *
 * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明:
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例1:
 *
 * 输入: 
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 *
 * 输入: 
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 *
 * @author douzhitong
 * @date 2021/3/2
 */
public class T134 {

    public static void main(String[] args) {
//        System.out.println(new T134().canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
//        System.out.println(new T134().canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
        System.out.println(new T134().canCompleteCircuit(new int[]{3,1,1}, new int[]{1,2,2}));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int negativeTotal = 0;
        int index = -1;
        for (int i = 0; i < gas.length; i++) {
            int remaining = gas[i] - cost[i];
            negativeTotal += remaining;
            if (negativeTotal >= 0) {
                if (index == -1) {
                    index = i;
                }
            } else {
                negativeTotal = 0;
                index = -1;
            }
            total += remaining;
        }
        return total >= 0 ? index : -1;
    }
}
