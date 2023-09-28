package com.xjhwang.springframework;

import com.xjhwang.springframework.beans.PropertyValue;
import com.xjhwang.springframework.beans.PropertyValues;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanReference;
import com.xjhwang.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.xjhwang.springframework.service.UserDao;
import com.xjhwang.springframework.service.UserService;
import org.junit.Test;

/**
 * @author xjhwang on 2023-09-28 14:11
 */
public class ApiTests {

    @Test
    public void Bean工厂测试() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // userDao
        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", userDaoBeanDefinition);

        // userService
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        userServiceBeanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
