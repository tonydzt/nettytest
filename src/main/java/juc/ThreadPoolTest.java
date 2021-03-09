package juc;
import java.util.concurrent.*;

/**
 * @author douzhitong
 * @date 2020/6/28
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        testSingle();
    }

    private static void testSingle() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i <= 5000; i++) {
            Future<String> future = executorService.submit(() -> {
                {
                    try {
                        Thread.sleep(1000);
                        System.out.println("over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        return "haha";
                    }
                }
            });
            try {
                System.out.println(future.get(1, TimeUnit.SECONDS));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
