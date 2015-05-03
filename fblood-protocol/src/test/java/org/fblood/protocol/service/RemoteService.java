package org.fblood.protocol.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by coder on 15/5/1.
 */
public interface RemoteService extends Remote {

    String sayHello() throws RemoteException;

}
