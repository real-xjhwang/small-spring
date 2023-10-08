package com.xjhwang.springframework.beans.factory.config;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 提供bean实例化之前对BeanDefinition修改的功能
 *
 * @author xjhwang on 2023-10-08 09:53
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有BeanDefinition加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     *
     * @param beanFactory 容器
     * @throws BeansException 执行过程中自定义抛出
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
