package game;

import java.math.BigDecimal;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.stream.DoubleStream;

/**
 * @author douzhitong
 * @date 2019/12/19
 */
public class DropProbability {

    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(8);
    private static final ConcurrentHashMap<Integer, Double> DROP_TIME_STATISTICS = new ConcurrentHashMap<>();
    private static final int EXPERIMENT_TIMES = 1000;
    private static final CountDownLatch COUNT = new CountDownLatch(EXPERIMENT_TIMES);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < EXPERIMENT_TIMES; i++) {
            threadPoolExecutor.submit(new TryToDrop());
        }
        COUNT.await();

        TreeMap<Integer, Double> treeMap = new TreeMap<>(DROP_TIME_STATISTICS);
//        System.out.println(treeMap);
        // 归一化
        treeMap.forEach((a, b) -> treeMap.put(a, new BigDecimal(b / EXPERIMENT_TIMES).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()));
        System.out.println(treeMap.values().stream().flatMapToDouble(a-> DoubleStream.of(a.doubleValue())).sum());
        System.out.println(treeMap.keySet());
        System.out.println(treeMap.values());
    }

    private static boolean dropSuccess() {
        //0-100
        Random random = new Random();
        int num = random.nextInt(100);
        return num <= 2;
    }

    private static int getDropTimes() {
        int i = 0;
        while (true) {
            i++;
            if (dropSuccess()) {
                System.out.println(String.format("Drop Success at %d times.", i));
                break;
            }
        }
        return i;
    }

    private static class TryToDrop implements Runnable {
        @Override
        public void run() {
            int aDropTimes = getDropTimes();
            int bDropTimes = getDropTimes();
            int maxTimes = Math.max(aDropTimes, bDropTimes);
            DROP_TIME_STATISTICS.merge(maxTimes, 1d, (a, b) -> a + b);
            COUNT.countDown();
        }
    }
}
