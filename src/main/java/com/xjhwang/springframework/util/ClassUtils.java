package com.xjhwang.springframework.util;

import cn.hutool.core.util.ClassUtil;

import java.util.Objects;

/**
 * @author xjhwang
 * @date 2023/10/5 15:11
 */
public class ClassUtils extends ClassUtil {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable tr) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        // No thread context class loader -> use class loader of this class.
        if (Objects.isNull(cl)) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * Check whether the specified class is a CGLIB-generated class.
     *
     * @param clazz the class to check
     * @return true mark CGLIB-generated class
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (Objects.nonNull(clazz) && isCglibProxyClass(clazz.getName()));
    }

    /**
     * Check whether the specified class name is a CGLIB-generated class.
     *
     * @param className the class name to check
     * @return true mark CGLIB-generated class
     */
    public static boolean isCglibProxyClass(String className) {
        return (StringUtils.isNotBlank(className) && className.contains("$$"));
    }
}
