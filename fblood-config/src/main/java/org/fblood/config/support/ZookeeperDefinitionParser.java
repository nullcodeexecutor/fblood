package org.fblood.config.support;

import org.fblood.config.bean.ZookeeperBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by yuantao on 2015/3/28.
 */
public class ZookeeperDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        String address = element.getAttribute("address");
        String timeout = element.getAttribute("timeout");

        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(ZookeeperBean.class.getName());
        beanDefinition.getPropertyValues().add("id", id);
        beanDefinition.getPropertyValues().add("address", address);
        beanDefinition.getPropertyValues().add("timeout", timeout);

        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }

}
