package org.fblood.config.bean;

import org.springframework.beans.BeansException;

/**
 * Created by yuantao on 2015/3/29.
 */
public class ApplicationBean implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    private String application;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            SpringContextHolder.setApplicationContext(applicationContext);
        }
    }
}
