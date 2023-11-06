package com.xjhwang.springframework.aop.framework;

/**
 * 代理接口，获取不同的代理类
 *
 * @author xjhwang
 * @date 2023/11/6 22:45
 */
public interface AopProxy {

    Object getProxy();
}
