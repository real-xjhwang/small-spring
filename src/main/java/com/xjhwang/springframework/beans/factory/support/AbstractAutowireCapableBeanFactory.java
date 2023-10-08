package com.xjhwang.springframework.beans.factory.support;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.PropertyValue;
import com.xjhwang.springframework.beans.PropertyValues;
import com.xjhwang.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanPostProcessor;
import com.xjhwang.springframework.beans.factory.config.BeanReference;
import com.xjhwang.springframework.util.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @author xjhwang on 2023-09-28 14:44
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            // 1. 返回null表示让之后的 BeanPostProcessor 都不执行
            // 2. 返回原始对象估计是为了防止getBean()拿不到对象
            if (Objects.isNull(current)) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {

        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (Objects.isNull(current)) {
                return result;
            }
            result = current;
        }
        return result;
    }

    public InstantiationStrategy getInstantiationStrategy() {

        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {

        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {

        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String name, Object[] args) {

        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            // FIXME 此处只是简单地判断了构造函数地入参个数，实际应该判断每个参数的类型
            if (Objects.nonNull(args) && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, name, constructorToUse, args);
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {

        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A依赖B，获取B的实例化
                    BeanReference beanReference = (BeanReference)value;
                    value = getBean(beanReference.getName());
                }
                // 属性填充
                BeanUtils.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        // 1. 执行 BeanPostProcessor 前置处理方法
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 2.FIXME 执行Bean对象的初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 3.执行 BeanPostProcessor 后置处理方法
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);

        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws BeansException {}
}
