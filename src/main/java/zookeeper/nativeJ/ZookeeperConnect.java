package zookeeper.nativeJ;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author douzhitong
 * @date 2019/1/23
 */
public class ZookeeperConnect implements Watcher {

    private static CountDownLatch connectSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperConnect());

        System.out.println(zookeeper.getState());

        connectSemaphore.await();

        System.out.println("Zookeeper session established");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectSemaphore.countDown();
        }
    }
}
