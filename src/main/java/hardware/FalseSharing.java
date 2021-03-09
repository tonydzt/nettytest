package hardware;

/**
 * @author douzhitong
 * @date 2020/8/31
 */
public final class FalseSharing implements Runnable
{
    public final static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static
    {
        for (int i = 0; i < longs.length; i++)
        {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex)
    {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception
    {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException
    {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            t.join();
        }
    }

    @Override
    public void run()
    {
        long i = ITERATIONS + 1;
        while (0 != --i)
        {
            longs[arrayIndex].value = i;
        }
    }

    // 把@sun.misc.Contended加到这里会有一定效果，但是效果没有增加Padding那么明显
    // public @sun.misc.Contended final static class VolatileLong
    public final static class VolatileLong
    {
        // @Contended在这里不起作用，因为@Contended是暗示多个变量不在同一个缓存行中，但这里只有一个
        public volatile long value = 0L;

        // 按原文的6个就不行，得是7个padding. 而且7个有时候也不行
//        public long p1, p2, p3, p4, p5, p6, p7; // comment out
    }

//    // 这样Padding效果就会稳定一些，但是要浪费掉更多空间，而且比Padding单边慢一些
//    static class LhsPadding {
//        protected long  p1, p2, p3, p4, p5, p6, p7;
//    }
//
//    static class VolatileLongMiddle extends LhsPadding {
//        public volatile long value = 0L;
//    }
//
//    public final static class VolatileLong extends VolatileLongMiddle {
//        protected long p9, p10, p11, p12, p13, p14, p15;
//    }
}
