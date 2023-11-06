package com.xjhwang.springframework.aop.framework;

import com.xjhwang.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author xjhwang
 * @date 2023/11/6 22:45
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {

        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {

        return Proxy.newProxyInstance(this.getClass().getClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getClass())) {
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(), method, args);
    }
}
