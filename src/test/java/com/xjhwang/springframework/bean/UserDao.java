package com.xjhwang.springframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xjhwang on 2023-09-28 16:55
 */
public class UserDao {

    private static final Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uId) {

        return hashMap.get(uId);
    }
}
