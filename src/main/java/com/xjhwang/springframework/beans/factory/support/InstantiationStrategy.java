package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略
 *
 * @author xjhwang on 2023-09-28 15:31
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String name, Constructor<?> constructor, Object[] args);
}
