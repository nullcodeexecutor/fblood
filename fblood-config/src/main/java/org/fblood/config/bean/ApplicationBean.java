package org.fblood.config.bean;

import org.apache.zookeeper.ZooKeeper;
import org.fblood.zookeeper.ZookeeperConnector;
import org.fblood.zookeeper.ZookeeperOperator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yuantao on 2015/3/29.
 */
public class ApplicationBean implements ApplicationContextAware, InitializingBean, DisposableBean {

    private String application;

    private String zk;

    private String protocol;

    public String getApplication() {
        return application;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getZk() {
        return zk;
    }

    public void setZk(String zk) {
        this.zk = zk;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            FBloodContextHolder.setApplicationContext(applicationContext);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        FBloodContextHolder.setApplicationBean(this);
        ZooKeeper zooKeeper = new ZookeeperConnector().connect(this.zk, 2000);
        ZookeeperOperator.initContext(zooKeeper);
        ZookeeperOperator.registryApp(zooKeeper, this.application);
        FBloodContextHolder.setZk(zooKeeper);
    }

    public void destroy() throws Exception {
        ZookeeperOperator.close(FBloodContextHolder.getZk());
    }
}
