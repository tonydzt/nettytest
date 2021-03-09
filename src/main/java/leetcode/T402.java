package leetcode;

/**
 * @author douzhitong
 * @date 2021/3/2
 */
public class T402 {

    public static void main(String[] args) {
//        System.out.println(new T402().removeKdigits("1432219", 3));
//        System.out.println(new T402().removeKdigits("10200", 1));
//        System.out.println(new T402().removeKdigits("10", 2));
//        System.out.println(new T402().removeKdigits("112", 1));
        System.out.println(new T402().removeKdigits("1234567890", 9));
//        System.out.println("01".substring(1));
    }

    public String removeKdigits(String num, int k) {
        if (k <= 0) {
            return removePreZero(num);
        } else {
            if (num.length() == 0 || "0".equals(num)) {
                return "0";
            }
            num = removedigits(num);
            return removePreZero(removeKdigits(num, k - 1));
        }
    }

    private String removePreZero(String num) {
        while (num.startsWith("0") && !"0".equals(num)) {
            num = num.substring(1);
        }
        return num;
    }

    private String removedigits(String num) {
        int index = 0;
        if (num.length() <= 1) {
            return "0";
        }
        while (index < num.length() - 1) {
            if (num.charAt(index) > num.charAt(index+1)) {
                return num.substring(0, index) + num.substring(index+1);
            }
            index++;
        }
        return num.substring(0, num.length() - 1);
    }
}
