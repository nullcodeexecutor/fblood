##fblood


####1 Getting Started
#####1.1 Introduction
&nbsp;&nbsp;&nbsp;&nbsp;Fblood是一个基于注册中心的高可用的java分布式服务框架。Fblood基于spring，使用zookeeper作为注册中心，目前只支持rmi协议。

**目前Fblood处于开发阶段**

| Date | Progress |
|---------------+------------------|
| 2015/05/14 | 基本打通各个模块，实现了远程服务功能 |

#####1.2 Hello World
&nbsp;&nbsp;&nbsp;&nbsp;Module ``fblood-demo-server`` 和 ``fblood-demo-client`` 分别是服务提供者和消费者的demo。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;服务提供者的spring配置如下。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:fblood="http://fblood.org/schema/fblood"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://fblood.org/schema/fblood http://fblood.org/schema/fblood/fblood.xsd">

    <context:annotation-config />

    <!-- 声明一个zookeeper实例 -->
    <fblood:zookeeper id="fbloodZk" address="192.168.30.110:2181"/>

    <!-- application标识一个fblood应用，该名称应该具有全局（同一个zookeeper集群）唯一性 -->
    <fblood:application id="fbloodTestApp" zk="fbloodZk"/>


    <!-- spring配置声明bean -->
    <bean id="helloService" class="org.fblood.demo.server.service.HelloServiceImpl"/>
    <bean id="userService" class="org.fblood.demo.server.service.UserServiceImpl"/>

    <!-- 服务提供者声明 id缺省为ref  实际在spring环境中一个provider对应一个bean，该bean的name为 application id+ "-" + 提供者id. 如 fbloodTestApp-helloService -->
    <fblood:provider app="fbloodTestApp" ref="helloService" port="9968"/>
    <fblood:provider id="userService" app="fbloodTestApp" ref="userService" port="9969"/>

</beans>
```
&nbsp;&nbsp;&nbsp;&nbsp;服务消费方配置如下。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:fblood="http://fblood.org/schema/fblood"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://fblood.org/schema/fblood http://fblood.org/schema/fblood/fblood.xsd">

    <context:annotation-config />

    <context:component-scan base-package="org.fblood.demo.client.controller"/>

    <fblood:zookeeper id="fbloodZk" address="192.168.30.110:2181"/>
    <!-- application id必须和服务提供方相同 -->
    <fblood:application id="fbloodTestApp" zk="fbloodZk"/>

    <!-- 开启通过注解自动注入消费者service功能 -->
    <bean id="fbloodConsumer" class="org.fblood.consumer.FBloodConsumer">
        <property name="app" ref="fbloodTestApp"/>
    </bean>

</beans>
```
&nbsp;&nbsp;&nbsp;&nbsp;服务消费方通过注解使用远程服务。

```java
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

    /**
     * 默认名字是该接口名首字母小写
     */
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
```

