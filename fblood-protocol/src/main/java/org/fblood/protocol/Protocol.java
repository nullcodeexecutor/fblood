package org.fblood.protocol;

import java.rmi.Remote;

/**
 * Created by coder on 15/5/1.
 */
public interface Protocol {

    void publishService(Object service, String serviceName) throws Exception;

    Object getService(String serviceName) throws Exception;


}
