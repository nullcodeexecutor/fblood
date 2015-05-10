package org.fblood.demo.server;

import org.fblood.demo.service.HelloService;
import org.fblood.model.Provider;
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
            Provider provider = new Provider();
            provider.setHost("192.168.30.107");
            provider.setAppName("fbloodTestApp");
            provider.setPort(9968);
            provider.setServiceName("fbloodHelloService");
            HelloService remoteService = (HelloService)protocol.getService(provider);
            System.out.println(remoteService.sayHello("wankgyuantao"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
