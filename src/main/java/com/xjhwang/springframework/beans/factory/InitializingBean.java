package com.xjhwang.springframework.beans.factory;

/**
 * @author xjhwang
 * @date 2023/10/10 23:51
 */
public interface InitializingBean {

    /**
     * 在Bean处理完属性填充后调用
     *
     * @throws Exception 执行过程异常
     */
    void afterPropertySet() throws Exception;
}
