package com.xjhwang.springframework.common;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.PropertyValue;
import com.xjhwang.springframework.beans.PropertyValues;
import com.xjhwang.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author xjhwang on 2023-10-08 17:28
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
