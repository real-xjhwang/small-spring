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
}
