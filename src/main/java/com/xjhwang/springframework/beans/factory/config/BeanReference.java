package com.xjhwang.springframework.beans.factory.config;

/**
 * @author xjhwang on 2023-09-28 16:45
 */
public class BeanReference {

    private final String name;

    public BeanReference(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
