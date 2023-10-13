package com.xjhwang.springframework.beans.factory;

/**
 * @author xjhwang
 * @date 2023/10/10 23:52
 */
public interface DisposableBean {

    /**
     * 容器销毁时调用
     *
     * @throws Exception 执行过程异常
     */
    void destroy() throws Exception;
}
