package game;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.stream.DoubleStream;

/**
 * @author douzhitong
 * @date 2019/12/19
 */
public class Permutation {

    private static final double DROP_RATE = 0.03;
    private static final int KEY_RANGE = 250;

    public static void main(String[] args) {

        TreeMap<Integer, Double> dropRateMap = new TreeMap<>();
        for (int i = 1; i <= KEY_RANGE; i++) {
            dropRateMap.put(i, dropRateForTimes(i));
        }

        System.out.println(dropRateMap.values().stream().flatMapToDouble(a-> DoubleStream.of(a.doubleValue())).sum());
        System.out.println(dropRateMap.keySet());
        System.out.println(dropRateMap.values());
    }

    private static double dropRateForTimes(int times) {
        return BigDecimal.valueOf(C(times, 1) * DROP_RATE * DROP_RATE * Math.pow((1 - DROP_RATE), times - 1) * 2).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static int A(int n, int m) {
        int result = 1;
        for (int i = m; i > 0; i--) {
            result *= n;
            n--;
        }
        return result;
    }

    private static int C(int n, int m)
    {
        int helf = n / 2;
        if (m > helf) {
            m = n - m;
        }
        int numerator = A(n, m);
        int denominator = A(m, m);
        return numerator / denominator;
    }
}
