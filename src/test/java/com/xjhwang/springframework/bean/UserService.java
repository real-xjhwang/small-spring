package com.xjhwang.springframework.bean;

import com.xjhwang.springframework.beans.factory.DisposableBean;
import com.xjhwang.springframework.beans.factory.InitializingBean;

/**
 * @author xjhwang on 2023-09-28 14:12
 */
public class UserService implements InitializingBean, DisposableBean {

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
}
