package com.rearwave.blog.component.utils;

import com.rearwave.blog.model.dto.AuthUserDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sunyi
 */
public final class WebUtils {


    public static HttpServletRequest getCurrentRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest();
    }


    public static Integer getUserId(){
       return getCurrentUser().getId();
    }


    public static AuthUserDto getCurrentUser(){
        String token = getCurrentRequest().getHeader("token");
        String user = RedisUtil.get(token);
        return null == user ? null
                :GSON.parseObject(user,AuthUserDto.class);
    }


    public static String getSessionId(){
        return getCurrentRequest().getRequestedSessionId();
    }
}
