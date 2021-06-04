package juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author douzhitong
 * @date 2021/5/13
 */
public class ConcurentHashMapTest {

    private static final Map<Integer, Logic> map = new ConcurrentHashMap<>(8);
    private static final Map<Integer, Holder<Logic>> holderMap = new ConcurrentHashMap<>(8);

    public static void main(String[] args) throws InterruptedException {
        // jvm热机
        initHolderMap(1);
        initMap(1);

        // 正式测试
        for (int i = 1; i <= 1000; i = i * 10) {
            // i用来模拟实例初始化加载的耗时（ms）
            initHolderMap(i);
            initMap(i);
        }
    }

    public static void initMap(int sleepTime) throws InterruptedException {
        map.clear();
        long start = System.currentTimeMillis();
        int num = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < num / 20; j++) {
                int finalI = i;
                new Thread(() -> {
                    if (map.get(finalI) == null) {
                        map.computeIfAbsent(finalI, k -> {
                            try {
                                return getLogics(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        });
                    }
                    countDownLatch.countDown();
                }).start();
            }
        }
        countDownLatch.await();
        System.out.println("InitMap Cost " + (System.currentTimeMillis() - start) + "ms, sleep time is " + sleepTime + " ms.");
    }

    public static void initHolderMap(int sleepTime) throws InterruptedException {
        holderMap.clear();
        long start = System.currentTimeMillis();
        int num = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < num / 20; j++) {
                int finalI = i;
                new Thread(() -> {
                    if (holderMap.get(finalI) == null) {
                        holderMap.putIfAbsent(finalI, new Holder<>());
                        final Holder<Logic> holder = holderMap.get(finalI);
                        Logic instance = holder.get();
                        if (instance == null) {
                            synchronized (holder) {
                                instance = holder.get();
                                if (instance == null) {
                                    try {
                                        instance = getLogic(sleepTime);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    holder.set(instance);
                                }
                            }
                        }
                    }
                    countDownLatch.countDown();
                }).start();
            }
        }
        countDownLatch.await();
        System.out.println("InitHolderMap Cost " + (System.currentTimeMillis() - start) + "ms, sleep time is " + sleepTime + " ms.");
    }

    private static Logic getLogic(int sleepTime) throws InterruptedException {
        Thread.sleep(sleepTime);
//        System.out.println("logic");
        return new Logic();
    }

    private synchronized static Logic getLogics(int sleepTime) throws InterruptedException {
        Thread.sleep(sleepTime);
//        System.out.println("logic");
        return new Logic();
    }

    static class Logic {
    }

    static class Holder<T> {
        private volatile T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }
}
