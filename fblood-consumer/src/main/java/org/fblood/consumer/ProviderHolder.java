package org.fblood.consumer;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.fblood.config.bean.FBloodContextHolder;
import org.fblood.model.Provider;
import org.fblood.zookeeper.ZookeeperOperator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by coder on 15/5/18.
 */
public class ProviderHolder {
    private ProviderHolder() {
    }

    private static Map<String, List<Provider>> providerMap;

    static {
        providerMap = new ConcurrentHashMap<String, List<Provider>>();
    }

    public static void initProvider(final String app, final String serviceName) {
        final ZooKeeper zooKeeper = FBloodContextHolder.getZk();
        final String key = getKey(app, serviceName);
        List<Provider> providers = ZookeeperOperator.findProvider(zooKeeper, app, serviceName, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (Event.EventType.NodeChildrenChanged == watchedEvent.getType()) {
                    List<Provider> providerList = ZookeeperOperator.findProvider(zooKeeper, app, serviceName);
                    System.out.println("watcher size:" + providerList.size());
                    providerMap.put(key, providerList);
                }
            }
        });
        providerMap.put(key, providers);
    }

    public static List<Provider> findProvider(String app, String seerviceName) {
        return providerMap.get(getKey(app, seerviceName));
    }

    private static String getKey(String app, String serviceName) {
        return app + "-" + serviceName;
    }

}
