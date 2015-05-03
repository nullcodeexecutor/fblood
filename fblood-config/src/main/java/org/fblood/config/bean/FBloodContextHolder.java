package org.fblood.config.bean;

import org.springframework.context.ApplicationContext;

/**
 * Created by yuantao on 2015/3/29.
 */
public class FBloodContextHolder {
    private static ApplicationContext applicationContext;
    private static ApplicationBean applicationBean;

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
