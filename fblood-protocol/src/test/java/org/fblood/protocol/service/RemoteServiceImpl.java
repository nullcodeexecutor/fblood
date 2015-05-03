package org.fblood.protocol.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by coder on 15/5/1.
 */
public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {
    public RemoteServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        System.out.println("has request:");
        return "Hello ";
    }
}
