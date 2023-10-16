package com.xjhwang.springframework.context;

import com.xjhwang.springframework.beans.factory.HierarchicalBeanFactory;
import com.xjhwang.springframework.beans.factory.ListableBeanFactory;
import com.xjhwang.springframework.core.io.ResourceLoader;

/**
 * @author xjhwang on 2023-10-08 14:06
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
