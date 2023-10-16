package com.xjhwang.springframework.context;

import java.util.EventListener;

/**
 * @author xjhwang
 * @date 2023/10/16 20:30
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
