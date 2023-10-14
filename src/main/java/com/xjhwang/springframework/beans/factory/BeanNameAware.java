package com.xjhwang.springframework.beans.factory;

/**
 * 实现此接口，可以感知到所属的BeanName
 *
 * @author xjhwang
 * @date 2023/10/14 15:39
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
