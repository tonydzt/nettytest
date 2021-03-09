package leetcode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class T9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    //求出回文数再判断相等
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int origin = x;

        int total = 0;
        while (x >= 1) {
            total = total * 10 + (x % 10);
            x = x / 10;
        }

        return  origin == total;
    }
}

