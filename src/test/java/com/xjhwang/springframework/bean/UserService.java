package com.xjhwang.springframework.bean;

/**
 * @author xjhwang on 2023-09-28 14:12
 */
public class UserService {

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

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

    public String queryUserInfo() {

        return userDao.queryUserName(uid);
    }
}
