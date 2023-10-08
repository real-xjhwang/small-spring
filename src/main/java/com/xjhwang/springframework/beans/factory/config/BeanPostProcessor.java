package com.xjhwang.springframework.beans.factory.config;

import com.xjhwang.springframework.beans.BeansException;

/**
 * 提供Bean对象初始化时修改的功能
 *
 * @author xjhwang on 2023-10-08 14:01
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return 处理后返回的对象
     * @throws BeansException 执行过程自定义抛出
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return 处理后返回的对象
     * @throws BeansException 执行过程自定义抛出
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
