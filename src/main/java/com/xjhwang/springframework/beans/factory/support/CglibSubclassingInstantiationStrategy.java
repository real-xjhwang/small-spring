package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * Cglib实例化
 *
 * @author xjhwang on 2023-09-28 15:37
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String name, Constructor<?> constructor, Object[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {

            @Override
            public int hashCode() {

                return super.hashCode();
            }
        });
        if (Objects.isNull(constructor)) {
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
