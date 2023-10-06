package com.xjhwang.springframework.beans.factory.xml;

import com.xjhwang.springframework.beans.BeansException;
import com.xjhwang.springframework.beans.PropertyValue;
import com.xjhwang.springframework.beans.factory.config.BeanDefinition;
import com.xjhwang.springframework.beans.factory.config.BeanReference;
import com.xjhwang.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.xjhwang.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.xjhwang.springframework.core.io.Resource;
import com.xjhwang.springframework.core.io.ResourceLoader;
import com.xjhwang.springframework.util.StringUtils;
import com.xjhwang.springframework.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xjhwang
 * @date 2023/10/5 15:43
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        } catch (ClassNotFoundException e) {
            throw new BeansException("ClassNotFoundException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtils.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            // 判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            // 解析标签
            Element bean = (Element)childNodes.item(i);
            // id
            String id = bean.getAttribute("id");
            // name
            String name = bean.getAttribute("name");
            // class name
            String className = bean.getAttribute("class");
            // 获取对应的class
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StringUtils.isNotEmpty(id) ? id : name;
            // 都不存在，使用类名的小驼峰
            if (StringUtils.isEmpty(beanName)) {
                beanName = StringUtils.lowerFirst(clazz.getSimpleName());
            }

            // 定义bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }
                // 解析标签
                Element property = (Element)bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：基本类型、引用对象
                Object value = StringUtils.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }
}
