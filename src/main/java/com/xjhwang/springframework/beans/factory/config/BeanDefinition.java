package com.xjhwang.springframework.beans.factory.config;

import com.xjhwang.springframework.beans.PropertyValues;

import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:08
 */
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;

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
