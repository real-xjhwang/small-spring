package com.xjhwang.springframework.beans;

/**
 * @author xjhwang
 * @date 2023/10/5 15:39
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
