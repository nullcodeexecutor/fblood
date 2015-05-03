package org.fblood.demo.server;

import org.fblood.demo.server.service.HelloService;
import org.fblood.protocol.ProtocolFactory;
import org.fblood.protocol.rmi.RMIProtocol;
import org.junit.Test;

/**
 * Created by coder on 15/5/4.
 */
public class ClientTest {

    @Test
    public void test() {

        try {
            RMIProtocol protocol = (RMIProtocol) ProtocolFactory.getProtocol("org.fblood.protocol.rmi.RMIProtocol");
            protocol.setHost("127.0.0.1");
            protocol.setPort(9968);
            HelloService remoteService = (HelloService)protocol.getService("fbloodTestApp/fbloodHelloService");
            System.out.println(remoteService.sayHello("wankgyuantao"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
