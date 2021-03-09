package zookeeper.nativeJ;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 重复创建节点会报错：
 * org.apache.zookeeper.KeeperException$NodeExistsException: KeeperErrorCode = NodeExists for /zk-test-ephemeral-
 *
 * 1、同步方式
 * 2、异步方式
 *
 * @author douzhitong
 * @date 2019/1/24
 */
public class ZookeeperCreateNode implements Watcher {

    private static CountDownLatch connectSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperCreateNode());
        connectSemaphore.await();

//        String path = syncCreate(zookeeper, "/tonytest1");
        asyncCreate(zookeeper, "/tonytest2");

        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void asyncCreate(ZooKeeper zookeeper, String path) {

        zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new IStringCallBack(), "I am context");
        System.out.println("Success create znode: " + path);
    }

    private static String syncCreate(ZooKeeper zookeeper, String path) throws KeeperException, InterruptedException {

        String path1 = zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode: " + path1);

//        String path2 = zookeeper.create("/zk-test-ephemeral-", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        String path2 = zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);

        String path3 = zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("Success create znode: " + path3);

        return path3;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectSemaphore.countDown();
        }
    }

    static class IStringCallBack implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
        }
    }
}
