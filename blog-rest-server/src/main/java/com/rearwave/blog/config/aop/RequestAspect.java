package com.rearwave.blog.config.aop;

import com.rearwave.blog.component.utils.GSON;
import com.rearwave.blog.component.utils.SnUtil;
import com.rearwave.blog.component.utils.WebUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 切面
 * @author sunyi
 */
@Aspect
@Log4j2
@Component
public class RequestAspect  {


    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();
        HttpServletRequest request = WebUtils.getCurrentRequest();
        Object result = null ;
        SnUtil.set(System.nanoTime());
        try {
            log.info("RWB_IN [\"{}\"] receive << [params:{},sn:{}]"
                    ,request.getRequestURI(),getParamString(request), SnUtil.get());
            result = pjp.proceed();
        }finally {
            log.info("RWB_OUT [\"{}\"] return >> [result:{},sn:{}] exec_time:{} ms"
                    ,request.getRequestURI(),result, SnUtil.get(),System.currentTimeMillis() - start);
            SnUtil.clear();
        }
        return result;
    }

    private String getParamString(HttpServletRequest request){
        String result = null;
        String method = request.getMethod();
        if (RequestMethod.POST.name().equalsIgnoreCase(method)) {
            String contentType = request.getContentType();
            if (contentType.contains("json")) {
                //应对 application/json的情况
                try {
                    result = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    result = GSON.toJSONString(request.getParameterMap());
                }
            } else {
                result = GSON.toJSONString(request.getParameterMap());
            }
        } else {
            result = GSON.toJSONString(request.getParameterMap());
        }
        return result;
    }
}




