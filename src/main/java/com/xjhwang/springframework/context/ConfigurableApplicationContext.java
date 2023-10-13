package com.xjhwang.springframework.context;

import com.xjhwang.springframework.beans.BeansException;

/**
 * @author xjhwang on 2023-10-08 14:08
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException 执行过程异常时抛出
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
