package org.fblood.config.support;

import org.fblood.config.bean.ProviderBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by yuantao on 2015/3/28.
 */
public class ProviderDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        String app = element.getAttribute("app");
        String ref = element.getAttribute("ref");
        int port = Integer.parseInt(element.getAttribute("port"));

        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(ProviderBean.class.getName());
        beanDefinition.getPropertyValues().add("id", id);
        beanDefinition.getPropertyValues().add("app", app);
        beanDefinition.getPropertyValues().add("ref", ref);
        beanDefinition.getPropertyValues().add("port", port);


        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);

        return beanDefinition;
    }

}
