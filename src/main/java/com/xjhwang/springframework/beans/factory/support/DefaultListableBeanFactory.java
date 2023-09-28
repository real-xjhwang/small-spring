package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:48
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String name) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (Objects.isNull(beanDefinition)) {
            throw new RuntimeException("No bean named '" + name + "' found in " + this);
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {

        beanDefinitionMap.put(name, beanDefinition);
    }
}
