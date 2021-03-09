package leetcode;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class T91 {

    //num(n) = num(n-1) + (if n-1 == 1 || 2)num(n-2) 斐波那契数列
    public static int numDecodings(String s) {

        int[] num = new int[s.length()];
        if (s.charAt(0) == '0') {
            return 0;
        }
        num[0] = 1;

        if (s.length() > 1 ) {
            if (s.charAt(1) == '0') {
                if (s.charAt(0) > '2') {
                    return 0;
                }
                num[1] = 1;
            } else if (s.charAt(0) == '1' || (s.charAt(0) == '2' && s.charAt(1) < '7')) {
                num[1] = 2;
            } else {
                num[1] = 1;
            }
        }

        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2') {
                    return 0;
                }
                num[i] = num[i-2];
            } else {
                num[i] = num[i-1] + (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) < '7') ? num[i-2] : 0);
            }
        }

        return num[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("10"));
        System.out.println(numDecodings("27"));
        System.out.println(numDecodings("100"));
        System.out.println(numDecodings("0"));
        System.out.println(numDecodings("110"));
        System.out.println(numDecodings("301"));
    }
}
