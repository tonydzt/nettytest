package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import java.util.concurrent.TimeUnit;

/**
 * 原理就是通过争抢分布式锁来实现leader选举，谁抢到谁就是leader..
 * 没有啥paxos或者zab算法
 *
 * @author douzhitong
 * @date 2021/4/15
 */
public class LeaderElectionTest {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.3.246.180:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        String path = "/tonytest/leaderElection";
        Stat stat = client.checkExists().forPath(path);
        //如果不存在这个路径，stat为null，创建新的节点路径。
        if (stat == null) {
            String s = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path);
        }
        for (int i = 0; i < 10; i++) {
            LeaderSelector leaderSelector = new LeaderSelector(client, path, new ElectionListener(i));
            leaderSelector.start();
            System.out.println(i + " begin to elect leader");
        }
        TimeUnit.MINUTES.sleep(5);
    }

    static class ElectionListener implements LeaderSelectorListener {

        private final int number;

        public ElectionListener(int number) {
            this.number = number;
        }
        @Override
        public void takeLeadership(CuratorFramework client) throws Exception {
            System.out.printf(number + " become leader!");
            TimeUnit.MINUTES.sleep(5);
        }
        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {

        }
    }
}
