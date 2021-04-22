package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author douzhitong
 * @date 2021/3/24
 */
public class Test {

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.3.246.180:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/tonytest", true);
    }
}
