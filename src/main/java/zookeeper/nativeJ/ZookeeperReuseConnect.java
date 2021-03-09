package zookeeper.nativeJ;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 现象：不断重新建连
 *
 * @author douzhitong
 * @date 2019/1/24
 */
public class ZookeeperReuseConnect implements Watcher {

    private static CountDownLatch connectSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperReuseConnect());
        connectSemaphore.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] password = zooKeeper.getSessionPasswd();

//        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperReuseConnect(), 1L, "test".getBytes());

        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperReuseConnect(), sessionId, password);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectSemaphore.countDown();
        }
    }
}
