package org.fblood.demo.client;

import org.fblood.demo.client.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by coder on 15/5/10.
 */
public class ClientBootstrap {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/*.xml");
        UserController controller = context.getBean(UserController.class);
        controller.save("wangyuantao");
        controller.hello("world");
    }


}