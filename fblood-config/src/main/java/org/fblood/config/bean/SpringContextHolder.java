package org.fblood.config.bean;

import org.springframework.context.ApplicationContext;

/**
 * Created by yuantao on 2015/3/29.
 */
public class SpringContextHolder {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
