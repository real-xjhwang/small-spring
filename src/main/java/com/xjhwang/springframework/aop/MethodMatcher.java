package com.xjhwang.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配类，用于找到表达式范围内匹配下的目标类和方法
 *
 * @author xjhwang
 * @date 2023/11/6 22:22
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
