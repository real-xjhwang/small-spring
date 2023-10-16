package com.xjhwang.springframework.context.event;

import com.xjhwang.springframework.beans.factory.BeanFactory;
import com.xjhwang.springframework.context.ApplicationEvent;
import com.xjhwang.springframework.context.ApplicationListener;

/**
 * @author xjhwang
 * @date 2023/10/16 23:20
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @Override
    public void multicastEvent(final ApplicationEvent event) {

        for (ApplicationListener<ApplicationEvent> applicationListener : getApplicationListeners(event)) {
            applicationListener.onApplicationEvent(event);
        }
    }

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {

        setBeanFactory(beanFactory);
    }
}
