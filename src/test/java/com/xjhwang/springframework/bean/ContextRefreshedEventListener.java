package com.xjhwang.springframework.bean;

import com.xjhwang.springframework.context.ApplicationListener;
import com.xjhwang.springframework.context.event.ContextRefreshedEvent;

/**
 * @author xjhwang
 * @date 2023/10/16 23:52
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
