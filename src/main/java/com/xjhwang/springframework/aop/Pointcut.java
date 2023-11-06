package com.xjhwang.springframework.aop;

/**
 * @author xjhwang
 * @date 2023/11/6 22:20
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
