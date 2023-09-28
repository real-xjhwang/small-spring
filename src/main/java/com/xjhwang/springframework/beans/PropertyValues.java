package com.xjhwang.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjhwang on 2023-09-28 16:42
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {

        propertyValues.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {

        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String name) {

        for (PropertyValue propertyValue : this.propertyValues) {
            if (propertyValue.getName().equals(name)) {
                return propertyValue;
            }
        }
        return null;
    }
}
