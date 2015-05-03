package org.fblood.protocol.rmi;

import org.fblood.protocol.Protocol;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by coder on 15/5/1.
 */
public class RMIProtocol implements Protocol {
    private int port;
    private String host;


    @Override
    public void publishService(Object service, String serviceName) throws Exception{
        Registry registry = LocateRegistry.createRegistry(this.port);
        registry.bind(serviceName, (Remote) service);
    }


    @Override
    public Object getService(String serviceName) throws Exception{
        Registry registry = LocateRegistry.getRegistry(this.host, this.port);
        return registry.lookup(serviceName);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
