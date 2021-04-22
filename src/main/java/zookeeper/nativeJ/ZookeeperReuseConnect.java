package zookeeper.nativeJ;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * 现象：不断重新建连
 *
 * @author douzhitong
 * @date 2019/1/24
 */
public class ZookeeperReuseConnect implements Watcher {

    private static CountDownLatch connectSemaphore = new CountDownLatch(1);

    /**
     * 客户端1和客户端2会争抢同一个sessionId，导致重复断连、重连
     */
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("10.3.246.180:2181", 50000, new ZookeeperReuseConnect());
        connectSemaphore.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] password = zooKeeper.getSessionPasswd();

        // 监听
        zooKeeper.exists("/im/test1", true);

        //手动断连网络，然后在另一台电脑Zookeeper客户端上修改节点“/im/test1”的data值，然后重连网络，看是否能收到数据变化的事件
        //结果是只要在会话过期时间内重新连接会话，都能收到事件

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
