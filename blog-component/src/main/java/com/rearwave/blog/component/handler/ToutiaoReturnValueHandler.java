package com.rearwave.blog.component.handler;

import com.rearwave.blog.component.annotation.EncryptBody;
import com.rearwave.blog.component.enums.PlatformEnum;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ToutiaoReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(EncryptBody.class)
                && methodParameter.getParameterAnnotation(EncryptBody.class).value()== PlatformEnum.TOUTIAO;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {

    }
}
