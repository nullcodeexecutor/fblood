package org.fblood.demo.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by coder on 15/5/3.
 */
public class ServerBootstrap {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:/spring/*.xml");
    }


}
