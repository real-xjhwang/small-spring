package com.xjhwang.springframework.beans.factory.config;

import com.xjhwang.springframework.beans.PropertyValues;

import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:08
 */
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;

    public Class<?> getBeanClass() {

        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {

        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {

        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {

        this.propertyValues = Objects.nonNull(propertyValues) ? propertyValues : new PropertyValues();
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public BeanDefinition() {}

    public BeanDefinition(Class<?> beanClass) {

        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {

        this.beanClass = beanClass;
        this.propertyValues = Objects.nonNull(propertyValues) ? propertyValues : new PropertyValues();
    }
}
