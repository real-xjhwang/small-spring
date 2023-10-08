package com.xjhwang.springframework.beans.factory;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.xjhwang.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author xjhwang
 * @date 2023/10/6 23:40
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, SingletonBeanRegistry {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
