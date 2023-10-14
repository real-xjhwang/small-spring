package com.xjhwang.springframework.bean;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.*;
import com.xjhwang.springframework.context.ApplicationContext;
import com.xjhwang.springframework.context.ApplicationContextAware;

/**
 * @author xjhwang on 2023-09-28 14:12
 */
public class UserService implements InitializingBean, DisposableBean, BeanFactoryAware, BeanClassLoaderAware, BeanNameAware, ApplicationContextAware {

    private BeanFactory beanFactory;

    private ClassLoader beanClassLoader;

    private String beanName;

    private ApplicationContext applicationContext;

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

    @Override
    public void destroy() throws Exception {

        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertySet() throws Exception {

        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public String queryUserInfo() {

        return userDao.queryUserName(uid);
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {

        return userDao;
    }

    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {

        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {

        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {

        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    public String getBeanName() {
        return beanName;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
