package org.fblood.config.bean;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.ApplicationContext;

/**
 * Created by yuantao on 2015/3/29.
 */
public class FBloodContextHolder {
    private static ApplicationContext applicationContext;
    private static ApplicationBean applicationBean;
    private static ZooKeeper zk;

    public static ZooKeeper getZk() {
        return zk;
    }

    public static void setZk(ZooKeeper zk) {
        FBloodContextHolder.zk = zk;
    }

    public static ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    public static void setApplicationBean(ApplicationBean applicationBean) {
        FBloodContextHolder.applicationBean = applicationBean;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        FBloodContextHolder.applicationContext = applicationContext;
    }
}
