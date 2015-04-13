package org.fblood.zookeeper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yuantao on 2015/4/11.
 */
public class ZookeeperConnector implements Watcher {
    private static final Log LOGGER = LogFactory.getLog(ZookeeperConnector.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public ZooKeeper connect(String hosts, int timeout) {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(hosts, timeout, this);
            latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("connect zookeeper timeout");
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            latch.countDown();
            LOGGER.info("zookeeper conected");
        }
    }
}
