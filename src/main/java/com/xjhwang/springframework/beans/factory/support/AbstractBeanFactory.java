package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.BeanFactory;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:35
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) {

        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {

        return doGetBean(name, args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {

        // FIXME 暂时先不实现
        return (T)getBean(name);
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String name, final Object[] args) {

        Object singleton = getSingleton(name);
        if (Objects.nonNull(singleton)) {
            return (T)singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T)createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);
}
