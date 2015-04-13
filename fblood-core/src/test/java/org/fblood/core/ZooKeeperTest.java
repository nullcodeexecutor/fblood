package org.fblood.core;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.fblood.zookeeper.ZookeeperConnector;
import org.fblood.zookeeper.ZookeeperOperator;
import org.junit.Test;

/**
 * Created by yuantao on 2015/4/11.
 */
public class ZooKeeperTest {

    @Test
    public void connect() {
        ZookeeperConnector connector = new ZookeeperConnector();
        ZooKeeper zooKeeper = connector.connect("127.0.0.1:2181", 2000);

        ZookeeperOperator.initContext(zooKeeper);
        ZookeeperOperator.destroy(zooKeeper);
    }

}
