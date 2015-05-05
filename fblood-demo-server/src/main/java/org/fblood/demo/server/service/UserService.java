package org.fblood.demo.server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by coder on 15/5/5.
 */
public interface UserService extends Remote {

    void save(String userName) throws RemoteException;

}
