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
import com.xjhwang.springframework.common.MyBeanFactoryPostProcessor;
import com.xjhwang.springframework.common.MyBeanPostProcessor;
import com.xjhwang.springframework.context.ApplicationContext;
import com.xjhwang.springframework.context.ConfigurableApplicationContext;
import com.xjhwang.springframework.context.support.ClassPathXmlApplicationContext;
import com.xjhwang.springframework.core.io.DefaultResourceLoader;
import com.xjhwang.springframework.core.io.Resource;
import com.xjhwang.springframework.core.io.ResourceLoader;
import com.xjhwang.springframework.util.IoUtils;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

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

    @Test
    public void 基于BeanFactory测试两大增强接口() {

        // 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置文件&注册Bean
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean 实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean 实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取 Bean 对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void 基于ApplicationContext测试两大接口() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void 测试初始化方法和销毁方法() {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        applicationContext.close();
    }

    @Test
    public void 测试感知接口() {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

        System.out.println("ApplicationContext: " + userService.getApplicationContext());
        System.out.println("beanFactory: " + userService.getBeanFactory());
        System.out.println("beanClassLoader: " + userService.getBeanClassLoader());
        System.out.println("beanName: " + userService.getBeanName());
        applicationContext.close();
    }

    @Test
    public void 测试Scope() {

        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        System.out.println(userService01);
        System.out.println(userService02);

        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(userService02 + " 十六进制哈希：" + Integer.toHexString(userService02.hashCode()));

        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

        applicationContext.close();
    }

    @Test
    public void 测试FactoryBean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
