//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hx.common.utils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyReader extends PropertyPlaceholderConfigurer implements Serializable {
    private static final long serialVersionUID = -4597092453265262225L;
    private static final Logger log = LoggerFactory.getLogger(PropertyReader.class);
    protected static Properties ctxProps = null;

    public PropertyReader() {
    }

    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        Iterator it = props.keySet().iterator();

        while(it.hasNext()) {
            String key = it.next().toString();
            String value = props.getProperty(key);
            log.debug("load context prop: k={};v={}", key, value);
        }

        log.debug("初始化PropertiesReader，开始加载properties配置文件中的数据！");
        ctxProps = props;
    }

    private static void assertInit() {
        if (ctxProps == null) {
            throw new IllegalStateException("当前PropertyReader中未进行properties文件注入，请在spring文件中进行配置！");
        }
    }

    public static String getContextProperty(String key) {
        assertInit();
        return ctxProps.getProperty(key);
    }
}
