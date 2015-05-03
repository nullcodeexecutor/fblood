package org.fblood.config.bean;

import org.fblood.protocol.Protocol;
import org.fblood.protocol.ProtocolFactory;
import org.fblood.protocol.rmi.RMIProtocol;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by coder on 15/5/1.
 */
public class ProviderBean implements InitializingBean {
    private String id;
    private String app;
    private String ref;
    private int port;


    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationBean applicationBean = FBloodContextHolder.getApplicationBean();
        Protocol protocol = ProtocolFactory.getProtocol(applicationBean.getProtocol());
        RMIProtocol rmiProtocol = (RMIProtocol)protocol;
        rmiProtocol.setPort(this.port);

        Object service = FBloodContextHolder.getApplicationContext().getBean(this.ref);

        String path = applicationBean.getApplication() + "/" + this.id;
        protocol.publishService(service, path);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
