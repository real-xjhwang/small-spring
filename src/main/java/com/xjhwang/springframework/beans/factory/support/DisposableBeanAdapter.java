package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.DisposableBean;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author xjhwang
 * @date 2023/10/11 0:01
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    @Override
    public void destroy() throws Exception {

        // 1. 实现了DisposableBean接口
        if (bean instanceof DisposableBean) {
            ((DisposableBean)bean).destroy();
        }

        // 配置了destroy-method，没有实现DisposableBean或者配置的destroy-method名称不是destroy，防止重复销毁
        if (StringUtils.isNotBlank(destroyMethodName) && !(bean instanceof DisposableBean && Objects.equals("destroy", destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (Objects.isNull(destroyMethod)) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethod + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }
}
