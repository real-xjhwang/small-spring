package com.xjhwang.springframework.context.event;

import com.xjhwang.springframework.context.ApplicationContext;
import com.xjhwang.springframework.context.ApplicationEvent;

/**
 * @author xjhwang
 * @date 2023/10/16 20:25
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {

        return (ApplicationContext)getSource();
    }
}
