package com.xjhwang.springframework.aop;

/**
 * 代理目标
 *
 * @author xjhwang
 * @date 2023/11/6 22:41
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?>[] getTargetClass() {

        return target.getClass().getInterfaces();
    }
}
