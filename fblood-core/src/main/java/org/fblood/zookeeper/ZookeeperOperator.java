package org.fblood.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.fblood.model.Provider;

import java.util.ArrayList;
import java.util.List;

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
            String appPath = root + "/" + app;
            if (null == zooKeeper.exists(appPath, false)) {
                zooKeeper.create(appPath, app.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static List<Provider> findProvider(ZooKeeper zookeeper, String appName, String serviceName, Watcher watcher) {
        String path = root + "/" + appName + "/" + serviceName;
        List<Provider> providers = new ArrayList<Provider>();
        try {
            List<String> children = zookeeper.getChildren(path, watcher);
            for (String childPath : children) {
                byte[] data = zookeeper.getData(path + "/" + childPath, true, null);
                Provider provider = JSON.parseObject(new String(data), Provider.class);
                providers.add(provider);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return providers;
    }


    public static List<Provider> findProvider(ZooKeeper zookeeper, String appName, String serviceName) {
        return findProvider(zookeeper, appName, serviceName, null);
    }


    public static void registryProvider(ZooKeeper zooKeeper, Provider provider) {
        try {
            String servicePath = root + "/" + provider.getAppName() + "/" + provider.getServiceName();
            String providerPath = servicePath + "/" + provider.getHost() + "_" + provider.getPort();
            if (null == zooKeeper.exists(servicePath, false)) {
                zooKeeper.create(servicePath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            Stat stat = zooKeeper.exists(providerPath, false);
            if (null != stat) {
                zooKeeper.delete(providerPath, stat.getVersion());
            }
            zooKeeper.create(providerPath, JSON.toJSONString(provider).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
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
