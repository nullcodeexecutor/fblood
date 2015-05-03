package org.fblood.config.support;

import org.fblood.config.bean.ApplicationBean;
import org.fblood.config.bean.ProviderBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by yuantao on 2015/3/28.
 */
public class AppDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String application = element.getAttribute("id");
        String zk = element.getAttribute("zookeeper");
        String protocol = element.getAttribute("protocol");

        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(ApplicationBean.class.getName());
        beanDefinition.getPropertyValues().add("application", application);
        beanDefinition.getPropertyValues().add("zk", zk);
        beanDefinition.getPropertyValues().add("protocol", protocol);

        parserContext.getRegistry().registerBeanDefinition(application, beanDefinition);

        return beanDefinition;
    }

}
