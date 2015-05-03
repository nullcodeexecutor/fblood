package org.fblood.protocol.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by coder on 15/5/1.
 */
public interface HelloService extends Remote {

    String sayHello(String name) throws RemoteException;

}
