package com.xjhwang.springframework.test.common;

import com.xjhwang.springframework.test.beans.UserService;
import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Objects;

/**
 * @author xjhwang on 2023-10-08 17:29
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (Objects.equals("userService", beanName)) {
            UserService userService = (UserService)bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
