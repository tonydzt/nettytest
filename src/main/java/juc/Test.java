package juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试多线程下的争抢和回调
 * @author douzhitong
 * @date 2021/1/10
 */
public class Test {

    private static final AtomicLong id = new AtomicLong(0L);
    private static final LinkedBlockingQueue<Fc> bl = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Long> c = new LinkedBlockingQueue<>();
//    private static final ConcurrentLinkedQueue<Fc> bl = new ConcurrentLinkedQueue<>();

    private static long a = 0;
    private static long start = 0L;

    public static Long getNextId(Integer appId) {
        return 1L;
    }

    // 单线程更快一些
    public static void getNextId1(Integer appId) {
        bl.offer(new Fc(Thread.currentThread()));
    }

    // 线程争抢，比单线程慢一倍
    public static Long getNextId2(Integer appId) {
        long num = id.incrementAndGet();
        if (num == 10001000L) {
            System.out.println("total cost " + (System.currentTimeMillis() - start) + "ms");
        }
        return num;
    }

    public static Long getNextId3(Integer appId) {
        long num = c.poll();
        if (num == 10001000L) {
            System.out.println("total cost " + (System.currentTimeMillis() - start) + "ms");
        }
        return num;
    }

    public static void main(String[] args) {
        int threadNum = 1000;
        CyclicBarrier barrier = new CyclicBarrier(threadNum + 1, new Runnable() {

            @Override
            public void run() {
                start = System.currentTimeMillis();
            }
        });

        for (long i = 1; i <= 10001000L; i++) {
            c.offer(i);
        }

        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
        new ConsumerThread(barrier).start();
    }

    static class Fc {

        private Thread t;

        Fc(Thread thread) {
            this.t = thread;
        }

        public void callBack() {
            long num = ++a;
            System.out.println(num);
            if (num == 10001000L) {
                System.out.println("total cost " + (System.currentTimeMillis() - start) + "ms");
            }
        }
    }

    static class TaskThread extends Thread {
        CyclicBarrier barrier;
        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
                for (int i = 0; i <= 10000; i++) {
                    long start = System.currentTimeMillis();
                    System.out.println(getNextId2(1));
//                    long cost = System.currentTimeMillis() - start;
//                    if (cost > 10) {
//                        System.out.println("cost is " + cost + "ms");
//                    }
//                    System.out.println(getNextId2(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ConsumerThread extends Thread {
        CyclicBarrier barrier;
        public ConsumerThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
                while (true) {
                    Fc function = bl.poll();
                    if (function != null) {
                        function.callBack();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
