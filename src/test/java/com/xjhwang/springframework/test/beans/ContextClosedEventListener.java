package com.xjhwang.springframework.test.beans;

import com.xjhwang.springframework.context.ApplicationListener;
import com.xjhwang.springframework.context.event.ContextClosedEvent;

/**
 * @author xjhwang
 * @date 2023/10/16 23:51
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
