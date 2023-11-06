package com.xjhwang.springframework.aop;

/**
 * 类匹配类，用于切点找到对应的接口和类
 *
 * @author xjhwang
 * @date 2023/11/6 22:21
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
