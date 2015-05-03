package org.fblood.config.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by yuantao on 2015/3/29.
 */
public class ApplicationBean implements ApplicationContextAware, InitializingBean {

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
    }
}
