package org.fblood.protocol;

import org.fblood.protocol.rmi.RMIProtocol;
import org.fblood.protocol.service.HelloService;
import org.fblood.protocol.service.HelloServiceImpl;
import org.fblood.protocol.service.RemoteService;
import org.fblood.protocol.service.RemoteServiceImpl;
import org.junit.Test;

/**
 * Created by coder on 15/5/1.
 */
public class ProtocolTest {

    @Test
    public void server() {
        try {
            RMIProtocol protocol = (RMIProtocol)ProtocolFactory.getProtocol("org.fblood.protocol.rmi.RMIProtocol");
            protocol.setHost("127.0.0.1");
            protocol.setPort(9968);
            HelloService remoteService = new HelloServiceImpl();
            protocol.publishService(remoteService, "app1/RemoteService1");
            Thread.sleep(1000 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void client() {
        try {
            RMIProtocol protocol = (RMIProtocol)ProtocolFactory.getProtocol("org.fblood.protocol.rmi.RMIProtocol");
            protocol.setHost("127.0.0.1");
            protocol.setPort(9968);
            HelloService remoteService = (HelloService)protocol.getService("fbloodTestApp/fbloodHelloService");
            System.out.println(remoteService.sayHello("wankgyuantao"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        System.out.println("===");
    }

}
