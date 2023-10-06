package com.xjhwang.springframework.beans.factory.config;

import com.xjhwang.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 可配置Bean的BeanFactory
 *
 * @author xjhwang
 * @date 2023/10/6 21:22
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
}
