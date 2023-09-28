package com.xjhwang.springframework.beans;

/**
 * @author xjhwang on 2023-09-28 16:41
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {

        this.name = name;
        this.value = value;
    }

    public String getName() {

        return name;
    }

    public Object getValue() {

        return value;
    }
}
