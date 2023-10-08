package com.xjhwang.springframework.beans.factory.config;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.BeanFactory;

/**
 * 具有自动装配bean的能力的BeanFactory
 *
 * @author xjhwang
 * @date 2023/10/6 21:18
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean 存在的bean对象
     * @param beanName     bean名称
     * @return 处理后的对象
     * @throws BeansException 执行过程自定义抛出
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessAfterInitialization 方法
     *
     * @param existingBean 存在的bean对象
     * @param beanName     bean名称
     * @return 处理后的对象
     * @throws BeansException 执行过程自定义抛出
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
