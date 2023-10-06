package com.xjhwang.springframework;

import com.xjhwang.springframework.bean.UserDao;
import com.xjhwang.springframework.bean.UserService;
import com.xjhwang.springframework.beans.PropertyValue;
import com.xjhwang.springframework.beans.PropertyValues;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanReference;
import com.xjhwang.springframework.beans.factory.support.BeanDefinitionReader;
import com.xjhwang.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.xjhwang.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.xjhwang.springframework.core.io.DefaultResourceLoader;
import com.xjhwang.springframework.core.io.Resource;
import com.xjhwang.springframework.core.io.ResourceLoader;
import com.xjhwang.springframework.util.IoUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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

    @Test
    public void Classpath文件加载测试() throws IOException {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtils.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void 系统文件加载测试() throws IOException {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("D:\\Workspace\\small-spring\\src\\test\\resources\\important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtils.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void Url文件加载测试() throws IOException {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtils.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void Xml配置文件注册Bean测试() {

        // 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件&注册Bean
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 获取Bean对象&调用
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
