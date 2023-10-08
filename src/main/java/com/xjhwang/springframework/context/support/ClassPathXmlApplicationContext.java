package com.xjhwang.springframework.context.support;

/**
 * @author xjhwang on 2023-10-08 17:25
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {}

    public ClassPathXmlApplicationContext(String configLocation) {

        this(new String[] {configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {

        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {

        return configLocations;
    }
}
