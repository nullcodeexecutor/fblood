package org.fblood.demo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by coder on 15/5/10.
 */
public class ClientBootstrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:/spring/*.xml");
    }


}