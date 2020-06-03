package com.rearwave.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * web程序的核心配置，配置拦截器、解析器、序列化工具等
 * @author sunyi
 */
@Configuration
public class RearWaveApplicationConfigure implements WebMvcConfigurer {


    /**
     * 添加自定义拦截器
     * @param registry
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    /**
     * 添加自定义参数解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

    /**
     * 自定义返回数据处理器
     * @param handlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","OPTIONS")
                .exposedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 自定义消息序列化工具 本项目使用Gson作为序列化工具
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        //设置支持的媒体类型
        gsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON
                ,MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON_UTF8,MediaType.TEXT_PLAIN));

       converters.add(gsonHttpMessageConverter);
    }



}
