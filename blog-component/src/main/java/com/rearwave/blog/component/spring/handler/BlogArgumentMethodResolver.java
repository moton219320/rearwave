package com.rearwave.blog.component.spring.handler;

import com.rearwave.blog.component.annotation.DecryptBody;
import com.rearwave.blog.component.enums.PlatformEnum;
import com.rearwave.blog.component.utils.GSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 参数解析器，登录相关接口解密专用
 * @author sunyi
 */
@Log4j2
public class BlogArgumentMethodResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DecryptBody.class)
                && PlatformEnum.BLOG == parameter.getParameterAnnotation(DecryptBody.class).value();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.notNull(request,"request object must be not null!");

        Class<?> clazz = parameter.getParameterType();
        //base64 解密入参
        String enc = StreamUtils.copyToString(request.getInputStream(),StandardCharsets.UTF_8);
        log.debug("收到的密文：{}",enc);
        byte[] body = Base64Utils.decodeFromString(enc.replaceAll("\"",""));

        return GSON.parseObject(new String(body, StandardCharsets.UTF_8),clazz);
    }
}
