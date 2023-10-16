package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.FactoryBean;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanPostProcessor;
import com.xjhwang.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.xjhwang.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:35
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) {

        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) {

        return doGetBean(beanName, args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {

        // FIXME 暂时先不实现
        return (T)getBean(beanName);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {

        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {

        return beanClassLoader;
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String beanName, final Object[] args) {

        Object sharedInstance = getSingleton(beanName);
        if (Objects.nonNull(sharedInstance)) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T)getObjectForBeanInstance(sharedInstance, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);
        return (T)getObjectForBeanInstance(bean, beanName);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {

        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if (Objects.isNull(object)) {
            FactoryBean<?> factoryBean = (FactoryBean<?>)beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }
}
