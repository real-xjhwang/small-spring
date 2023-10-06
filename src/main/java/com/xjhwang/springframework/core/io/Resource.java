package com.xjhwang.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xjhwang
 * @date 2023/10/5 14:58
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
