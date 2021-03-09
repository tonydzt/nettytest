package leetcode;

/**
 * @author douzhitong
 * @date 2019/6/27
 */
public class T122 {

    public static int maxProfit(int[] prices) {
        int total = 0;
        if (prices.length == 0) {
            return total;
        }

        //无股票的状态
        int income = -1;
        for (int i = 0; i < prices.length; i++) {
            //最后一天，如果手里有股票，卖出
            if (i == prices.length - 1) {
                if (income > -1) {
                    total += (prices[i] - income);
                }
                break;
            }

            //卖出时机
            if (income > -1 && income < prices[i]) {
                total += (prices[i] - income);
                income = -1;
            }

            //买入时机
            if (income == -1 && prices[i] < prices[i + 1]) {
                income = prices[i];
            }
        }

        return total;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));
    }
}
