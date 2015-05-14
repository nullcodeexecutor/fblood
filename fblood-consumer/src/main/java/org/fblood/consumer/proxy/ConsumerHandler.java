package org.fblood.consumer.proxy;

import org.apache.zookeeper.ZooKeeper;
import org.fblood.config.bean.FBloodContextHolder;
import org.fblood.consumer.balance.LoadBalance;
import org.fblood.consumer.balance.RandomLoadBalance;
import org.fblood.model.Provider;
import org.fblood.protocol.Protocol;
import org.fblood.protocol.ProtocolFactory;
import org.fblood.zookeeper.ZookeeperOperator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by coder on 15/5/14.
 */
public class ConsumerHandler implements InvocationHandler {
    private String appName;
    private String serviceName;
    private LoadBalance loadBalance;

    public ConsumerHandler(String appName, String serviceName) {
        this.appName = appName;
        this.serviceName = serviceName;
        this.loadBalance = new RandomLoadBalance();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ZooKeeper zooKeeper = FBloodContextHolder.getZk();
        List<Provider> providerList = ZookeeperOperator.findProvider(zooKeeper, appName, serviceName);
        Provider provider = loadBalance.balance(providerList);

        Protocol protocol = ProtocolFactory.getProtocol(FBloodContextHolder.getApplicationBean().getProtocol());
        Object obj = protocol.getService(provider);
        return method.invoke(obj, args);
    }

}
