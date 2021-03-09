package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author douzhitong
 * @date 2021/3/9
 */
public class M1716 {

    private Map<Integer, Integer> maxMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new M1716().massage(new int[]{226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124}));
    }

    public int massage(int[] nums) {
        return max(nums, 0);
    }

    private int max(int[] nums, int fromIndex) {
        if (maxMap.containsKey(fromIndex)) {
            return maxMap.get(fromIndex);
        }
        if (fromIndex >= nums.length) {
            return 0;
        } else if (fromIndex == nums.length - 1) {
            return nums[nums.length - 1];
        }

        int result = Math.max(nums[fromIndex] + max(nums, fromIndex + 2), nums[fromIndex+1] + max(nums, fromIndex + 3));
        maxMap.put(fromIndex, result);
        return result;
    }
}
