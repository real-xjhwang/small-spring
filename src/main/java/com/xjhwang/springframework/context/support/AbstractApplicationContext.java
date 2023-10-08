package com.xjhwang.springframework.context.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.xjhwang.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.xjhwang.springframework.beans.factory.config.BeanPostProcessor;
import com.xjhwang.springframework.context.ConfigurableApplicationContext;
import com.xjhwang.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author xjhwang on 2023-10-08 14:07
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {

        // 1.创建BeanFactory并加载BeanDefinition
        refreshBeanFactory();

        // 2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.在Bean实例化之前，执行注册的BeanFactoryPostProcessor，进行BeanDefinition的处理
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4.BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 5.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {

        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {

        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) {

        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {

        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {

        return getBeanFactory().getBean(beanName, requiredType);
    }
}
