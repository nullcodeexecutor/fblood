package org.fblood.consumer;

import org.apache.commons.lang.StringUtils;
import org.fblood.config.bean.ApplicationBean;
import org.fblood.consumer.annotation.RemoteInject;
import org.fblood.consumer.proxy.ConsumerHandler;
import org.fblood.consumer.proxy.ProxyUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by yuantao on 2015/3/29.
 */
public class FBloodConsumer implements BeanPostProcessor {
    private ApplicationBean app;


    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        for (Field field : o.getClass().getDeclaredFields()) {
            RemoteInject remoteInject = field.getAnnotation(RemoteInject.class);
            if (null == remoteInject) {
                continue;
            }
            String serviceName = remoteInject.value();
            if (StringUtils.isBlank(serviceName)) {
                serviceName = firstLetterToLowerCase(field.getName());
            }
            ProviderHolder.initProvider(app.getApplication(), serviceName);
            Object proxy = ProxyUtil.getProxy(field.getType(), app.getApplication(), serviceName);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, o, proxy);
        }
        return o;
    }

    private String firstLetterToLowerCase(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    }

    public void setApp(ApplicationBean app) {
        this.app = app;
    }
}
