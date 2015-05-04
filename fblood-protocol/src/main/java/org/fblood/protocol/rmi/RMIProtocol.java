package org.fblood.protocol.rmi;

import org.fblood.model.Provider;
import org.fblood.protocol.Protocol;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by coder on 15/5/1.
 */
public class RMIProtocol implements Protocol {

    public void publishService(Provider provider, Object service) throws Exception {
        Registry registry = LocateRegistry.createRegistry(provider.getPort());
        registry.bind(provider.getAppName() + "/" + provider.getServiceName(), (Remote) service);

    }

    public Object getService(Provider provider) throws Exception {
        Registry registry = LocateRegistry.getRegistry(provider.getHost(), provider.getPort());
        return registry.lookup(provider.getAppName() + "/" + provider.getServiceName());
    }

}
