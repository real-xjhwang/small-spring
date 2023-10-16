package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xjhwang
 * @date 2023/10/15 17:45
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name -> object
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getObjectFromFactoryBean(FactoryBean<?> factoryBean, String beanName) {

        if (factoryBean.isSingleton()) {
            Object object = getCachedObjectForFactoryBean(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != null ? object : NULL_OBJECT);
        } else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    protected Object getCachedObjectForFactoryBean(String beanName) {

        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean<?> factoryBean, final String beanName) {

        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
