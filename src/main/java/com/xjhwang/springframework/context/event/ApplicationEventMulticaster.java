package com.xjhwang.springframework.context.event;

import com.xjhwang.springframework.context.ApplicationEvent;
import com.xjhwang.springframework.context.ApplicationListener;

/**
 * @author xjhwang
 * @date 2023/10/16 20:27
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     *
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     *
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
