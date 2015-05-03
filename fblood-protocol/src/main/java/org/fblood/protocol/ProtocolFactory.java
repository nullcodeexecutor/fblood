package org.fblood.protocol;

/**
 * Created by coder on 15/5/1.
 */
public class ProtocolFactory {

    public static Protocol getProtocol(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        return (Protocol)clazz.newInstance();
    }

}
