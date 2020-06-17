package com.rearwave.blog.config;

import com.github.pagehelper.PageInterceptor;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.google.gson.*;
import com.rearwave.blog.component.spring.handler.BlogArgumentMethodResolver;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * web程序的核心配置，配置拦截器、解析器、序列化工具等
 * @author sunyi
 */
@Log4j2
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
        /* 添加登录相关接口参数解析器 */
        argumentResolvers.add(new BlogArgumentMethodResolver());
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
     * @param
     */
    private CorsConfiguration corsConfig() {
        CorsConfiguration cors = new CorsConfiguration();
    /* 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等） */
        cors.addAllowedOrigin("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod(HttpMethod.GET);
        cors.addAllowedMethod(HttpMethod.POST);
        cors.addAllowedMethod(HttpMethod.OPTIONS);
        cors.setAllowCredentials(true);
        cors.setMaxAge(3600L);
        return cors;
    }

    @Bean
    public FilterRegistrationBean<?> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    /**
     * 自定义消息序列化工具 本项目使用Gson作为序列化工具
     * @param converters 消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        //序列化日期为时间戳
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class,(JsonSerializer<Date>) (date, type, jsonSerializationContext)
                        -> new JsonPrimitive(date.getTime()))
                .setDateFormat(DateFormat.LONG)
                //兼容时间戳
                /*.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, jsonDeserializationContext)
                        -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .registerTypeAdapter(Date.class,(JsonDeserializer<Date>) (jsonElement, type, jsonDeserializationContext)
                        -> new Date(jsonElement.getAsString()))*/
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();
        gsonHttpMessageConverter.setGson(gson);
        //设置支持的媒体类型
        gsonHttpMessageConverter.setSupportedMediaTypes(
                List.of(MediaType.APPLICATION_JSON
                        ,MediaType.APPLICATION_FORM_URLENCODED
                        ,MediaType.TEXT_PLAIN));
        //将Gson转换器放到第一的位置
        converters.add(0,gsonHttpMessageConverter);
    }

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "gray");
        properties.setProperty("kaptcha.textproducer.font.color", "orange");
        properties.setProperty("kaptcha.image.width", "120");
        properties.setProperty("kaptcha.image.height", "39");
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        properties.setProperty("kaptcha.textproducer.font.size", "35");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑,Consolas");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }


    @Bean
    public PageInterceptor paginationInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        /*
         * 默认为false，会将RowBounds第一个参数offset当成pageNum页面使用
         * 和startPage中的pageNum效果一样
         */
        properties.setProperty("offsetAsPageNum","true");
        /*RowBounds方式是否做count查询 默认false*/
        properties.setProperty("rowBoundsWithCount","true");
        /*分页合理化，true开启，如果分页参数不合理会自动修正。默认false不启用*/
        properties.setProperty("reasonable","true");
        /*是否允许接口方法参数来传递分页参数 默认false*/
        properties.setProperty("supportMethodsArguments","true");
        pageInterceptor.setProperties(properties);
        /*当设置为true的时候，如果pageSize设置为0（或RowBounds的limit=0），就不执行分页*/
        properties.setProperty("pageSizeZero","true");
        return pageInterceptor;
    }
}
