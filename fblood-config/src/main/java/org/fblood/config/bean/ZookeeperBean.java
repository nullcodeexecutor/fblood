package org.fblood.config.bean;

/**
 * Created by yuantao on 2015/3/29.
 */
public class ZookeeperBean  {

    private String id;

    private String address;

    private int timeout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
