package com.xjhwang.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.xjhwang.springframework.beans.PropertyValue;
import com.xjhwang.springframework.beans.PropertyValues;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:44
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {

        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, name, args);
            // 填充属性
            applyPropertyValues(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException("Instantiation of bean failed", e);
        }

        addSingleton(name, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String name, Object[] args) {

        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            if (Objects.nonNull(args) && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, name, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {

        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A依赖B，获取B的实例化
                    BeanReference beanReference = (BeanReference)value;
                    value = getBean(beanReference.getName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {

        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {

        this.instantiationStrategy = instantiationStrategy;
    }
}
