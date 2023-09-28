package com.xjhwang.springframework.service;

/**
 * @author xjhwang on 2023-09-28 14:12
 */
public class UserService {

    private String uid;

    private UserDao userDao;

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }

    public UserDao getUserDao() {

        return userDao;
    }

    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;
    }

    public void queryUserInfo() {

        System.out.println("用户信息查询：" + userDao.queryUserName(uid));
    }
}
