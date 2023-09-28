package com.xjhwang.springframework.beans.factory.config;

/**
 * @author xjhwang on 2023-09-28 14:35
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);
}
