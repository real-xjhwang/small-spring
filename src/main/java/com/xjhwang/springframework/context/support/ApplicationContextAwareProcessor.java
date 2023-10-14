package com.xjhwang.springframework.context.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.config.BeanPostProcessor;
import com.xjhwang.springframework.context.ApplicationContext;
import com.xjhwang.springframework.context.ApplicationContextAware;

/**
 * 由于ApplicationContext的获取不能在创建Bean的时候拿到，所以需要在refresh操作时， 把ApplicationContext写入到一个包装的BeanPostProcessor中，
 * 再由AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization时调用
 *
 * @author xjhwang
 * @date 2023/10/14 15:42
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware)bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
