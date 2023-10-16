package com.xjhwang.springframework.context;

import java.util.EventObject;

/**
 * @author xjhwang
 * @date 2023/10/16 20:24
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
