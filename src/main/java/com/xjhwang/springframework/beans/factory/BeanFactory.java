package com.xjhwang.springframework.beans.factory;

import com.xjhwang.springframework.beans.BeansException;

/**
 * @author xjhwang on 2023-09-28 14:08
 */
public interface BeanFactory {

    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);

    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;
}
