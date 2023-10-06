package com.xjhwang.springframework.core.io;

/**
 * @author xjhwang
 * @date 2023/10/5 15:29
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
