package com.xjhwang.springframework.beans.factory;

/**
 * @author xjhwang on 2023-09-28 14:08
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name, Object... args);
}
