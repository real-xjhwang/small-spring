package com.xjhwang.springframework.beans.factory;

/**
 * @author xjhwang
 * @date 2023/10/15 17:44
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
