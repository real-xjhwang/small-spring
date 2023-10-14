package com.xjhwang.springframework.beans.factory;

/**
 * 实现此接口，可以感知到所属的ClassLoader
 *
 * @author xjhwang
 * @date 2023/10/14 15:38
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader beanClassLoader);
}
