package org.fblood.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.fblood.model.Provider;

/**
 * Created by yuantao on 2015/4/11.
 */
public class ZookeeperOperator {
    private static String root = "/fblood_provider";

    public static synchronized void initContext(ZooKeeper zooKeeper) {
        try {
            if (null == zooKeeper.exists(root, false)) {
                zooKeeper.create(root, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void registryApp(ZooKeeper zooKeeper, String app) {
        try {
            zooKeeper.create(root + "/" + app, app.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void registryProvider(ZooKeeper zooKeeper, Provider provider) {
        try {
            zooKeeper.create(root + "/" + provider.getAppName() + "/" + provider.getServiceName(), JSON.toJSONString(provider).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void registryConsumer(ZooKeeper zooKeeper, Watcher watcher) {
        zooKeeper.register(watcher);
    }

    public static void destroy(ZooKeeper zooKeeper) {
        close(zooKeeper);
    }

    public static void close(ZooKeeper zooKeeper) {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
