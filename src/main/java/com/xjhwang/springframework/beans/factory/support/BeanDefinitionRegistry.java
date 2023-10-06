package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;

/**
 * @author xjhwang on 2023-09-28 14:49
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册BeanDefinition
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 根据bean名称查询BeanDefinition
     *
     * @param beanName bean名称
     * @return bean定义
     * @throws BeansException bean定义不存在时抛出
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     *
     * @param beanName bean名称
     * @return true表示存在
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取注册表中所有的bean名称
     *
     * @return 注册表中所有的bean名称，不存在返回空数组
     */
    String[] getBeanDefinitionNames();
}
