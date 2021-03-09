package leetcode.math;

/**
 * @author douzhitong
 * @date 2021/3/4
 */
public class T1295 {

    public static void main(String[] args) {
        System.out.println(new T1295().findNumbers(new int[]{12,345,2,6,7896}));
    }

    public int findNumbers(int[] nums) {
        int total = 0;
        for (int num : nums) {
            int digit = 0;
            while (num != 0) {
                num = num / 10;
                digit++;
            }
            if (digit % 2 == 0) {
                total++;
            }
        }
        return total;
    }
}
