package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xjhwang on 2023-09-28 14:37
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String name) {

        return singletonObjects.get(name);
    }

    protected void addSingleton(String name, Object singletonObject) {

        singletonObjects.put(name, singletonObject);
    }
}
