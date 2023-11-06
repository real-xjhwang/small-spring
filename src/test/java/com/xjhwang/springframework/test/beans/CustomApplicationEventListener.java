package com.xjhwang.springframework.test.beans;

import com.xjhwang.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author xjhwang
 * @date 2023/10/16 23:03
 */
public class CustomApplicationEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
