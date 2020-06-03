package com.rearwave.blog.component.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringValueResolver;

/**
 * spring上下文工具类，用来获取注册的 bean 或 property
 * 自身也必须声明称一个bean 才能正常注入
 * @author sunyi
 */
@Component
public class SpringUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static ApplicationContext context;

    private static StringValueResolver stringValueResolver;

    /**
     * 注入应用上下文对象
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    /**
     * 注入配置解析器
     * @param resolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }

    /**
     * 获取 Spring bean
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> target){
        Assert.notNull(context,"ApplicationContext must be not null");
        return context.getBean(target);
    }

    /**
     * 获取配置信息 yaml 或 properties 文件里的配置
     * @param key
     * @return
     */
    public static String getProperty(String key){
        Assert.notNull(stringValueResolver,"stringValueResolver must be not null");
        return stringValueResolver.resolveStringValue(String.format("${%s}", key));
    }
}
