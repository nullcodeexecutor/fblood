package org.fblood.demo.client.controller;

import org.fblood.consumer.annotation.RemoteInject;
import org.fblood.demo.service.HelloService;
import org.fblood.demo.service.UserService;
import org.springframework.stereotype.Controller;

import java.rmi.RemoteException;

/**
 * Created by coder on 15/5/10.
 */
@Controller
public class UserController {

    @RemoteInject
    private UserService userService;

    @RemoteInject("helloService")
    private HelloService helloService;

    public void save(String name) {
        try {
            userService.save(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void hello(String name) {
        try {
            String response = helloService.sayHello(name);
            System.out.println(response);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
