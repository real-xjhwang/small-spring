package com.xjhwang.springframework.beans.factory;

/**
 * 实现此接口，可以感知到所属的BeanFactory
 *
 * @author xjhwang
 * @date 2023/10/14 15:37
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);
}
