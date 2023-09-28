package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.factory.config.BeanDefinition;

/**
 * @author xjhwang on 2023-09-28 14:49
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
