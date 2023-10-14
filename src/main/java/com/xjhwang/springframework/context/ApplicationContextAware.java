package com.xjhwang.springframework.context;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.factory.Aware;

/**
 * 实现此接口，可以感知到所属的ApplicationContext
 *
 * @author xjhwang
 * @date 2023/10/14 15:40
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
