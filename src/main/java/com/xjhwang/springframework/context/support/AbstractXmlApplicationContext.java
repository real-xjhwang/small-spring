package com.xjhwang.springframework.context.support;

import com.xjhwang.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.xjhwang.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Objects;

/**
 * @author xjhwang on 2023-10-08 17:23
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (Objects.nonNull(configLocations)) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
