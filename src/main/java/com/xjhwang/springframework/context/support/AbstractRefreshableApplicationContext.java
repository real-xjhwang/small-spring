package com.xjhwang.springframework.context.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.xjhwang.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author xjhwang on 2023-10-08 17:20
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {

        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {

        return beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {

        return new DefaultListableBeanFactory();
    }
}
