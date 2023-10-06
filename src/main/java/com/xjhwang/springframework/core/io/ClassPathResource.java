package com.xjhwang.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.xjhwang.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author xjhwang
 * @date 2023/10/5 14:58
 */
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (Objects.isNull(inputStream)) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return inputStream;
    }

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (Objects.nonNull(classLoader) ? classLoader : ClassUtils.getDefaultClassLoader());
    }
}
