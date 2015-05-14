package org.fblood.consumer.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by coder on 15/5/14.
 */
public class ProxyUtil {
    private ProxyUtil() {
    }


    public static Object getProxy(Class<?> clazz, String app, String serviceName) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new ConsumerHandler(app, serviceName));
    }

}
