package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author douzhitong
 * @date 2021/3/22
 */
public class PathChildrenCacheTest {

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.3.246.180:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        String path = "/tonytest";
        //检测是否存在该路径。
        Stat stat = client.checkExists().forPath(path);

        //如果不存在这个路径，stat为null，创建新的节点路径。
        if (stat == null) {
            String s = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path);
        }

        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.getListenable().addListener((curatorFramework, event) -> {
            System.out.println("======== catch children change =======");
            if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED ||
                    event.getType() == PathChildrenCacheEvent.Type.CHILD_UPDATED ||
                    event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                System.out.println("update event type:" + event.getType() +
                        ",path:" + event.getData().getPath() + ",data:" + new String(event.getData().getData(), CHARSET));
            } else {
                System.out.println("update event type:" + event.getType());
            }
        });
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        TimeUnit.MINUTES.sleep(5);
    }
}
