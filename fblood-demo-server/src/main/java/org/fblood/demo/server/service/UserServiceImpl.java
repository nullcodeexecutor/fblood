package org.fblood.demo.server.service;

import org.fblood.demo.service.UserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by coder on 15/5/5.
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    public UserServiceImpl() throws RemoteException {
    }

    @Override
    public void save(String userName) throws RemoteException {
        System.out.println("save " + userName);
    }
}
