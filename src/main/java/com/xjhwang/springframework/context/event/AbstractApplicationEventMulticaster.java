package com.xjhwang.springframework.context.event;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.BeanFactory;
import com.xjhwang.springframework.beans.factory.BeanFactoryAware;
import com.xjhwang.springframework.context.ApplicationEvent;
import com.xjhwang.springframework.context.ApplicationListener;
import com.xjhwang.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author xjhwang
 * @date 2023/10/16 20:32
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {

        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {

        this.applicationListeners.add((ApplicationListener<ApplicationEvent>)listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {

        this.applicationListeners.remove(listener);
    }

    /**
     * Return a Collection of ApplicationListeners matching the given event type. Non-matching listeners get excluded early.
     *
     * @param event the event to be propagated. Allows for excluding non-matching listeners early, based on cached matching information.
     * @return a Collection of ApplicationListeners
     * @see com.xjhwang.springframework.context.ApplicationListener
     */
    protected Collection<ApplicationListener<ApplicationEvent>> getApplicationListeners(ApplicationEvent event) {

        LinkedList<ApplicationListener<ApplicationEvent>> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> applicationListener : this.applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                allListeners.add(applicationListener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     *
     * @param listener 监听器
     * @param event    事件
     * @return true表示感兴趣
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {

        Class<? extends ApplicationListener> listenerClass = listener.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy，不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType)genericInterface).getActualTypeArguments()[0];
        String eventClassName = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            eventClass = Class.forName(eventClassName);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + eventClassName);
        }
        // 判定此 eventClassName 对象所表示的类或者接口与指定的 event.getClass() 参数所表示的类或者接口是否相同，或是否是其超类或超接口
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClass.isAssignableFrom(event.getClass());
    }
}
