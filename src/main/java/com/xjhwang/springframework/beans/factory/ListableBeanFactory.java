package com.xjhwang.springframework.beans.factory;

import com.xjhwang.springframework.beans.BeansException;

import java.util.Map;

/**
 * 职责：提供容器中bean批量操作的功能
 *
 * @author xjhwang
 * @date 2023/10/6 17:47
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回Bean实例
     *
     * @param type bean类型
     * @param <T>  bean类型泛型
     * @return bean实例
     * @throws BeansException 不存在时返回
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取注册表中所有的bean名称
     *
     * @return 注册表中所有的bean名称，不存在返回空数组
     */
    String[] getBeanDefinitionNames();
}
