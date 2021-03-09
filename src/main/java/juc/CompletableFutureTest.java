package juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture虽然会并行，但是使用线程池以及合并操作感觉会在业务时间之外额外多出100多ms，
 * 对于要求高响应速度的接口得不偿失，适用于响应不是那么快的接口
 *
 * @author douzhitong
 * @date 2021/1/20
 */
public class CompletableFutureTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String result = getAllSync();
        String result = getAllAsync();
        System.out.println("Cost " + (System.currentTimeMillis() - start) + " ms, result is " + result);
    }

    public static String getAllSync() {
        String a = get1();
        String b = get2();
        String c = get3();
        return a + b + c;
    }

    public static String getAllAsync() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(CompletableFutureTest::get1, executor);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(CompletableFutureTest::get2, executor);
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(CompletableFutureTest::get3, executor);
        CompletableFuture.allOf(f1,f2,f3).join();
        try {
            String a = f1.get();
            String b = f2.get();
            String c = f3.get();
            return a + b + c;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "1";
    }

    public static String get2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "2";
    }

    public static String get3() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "3";
    }
}
