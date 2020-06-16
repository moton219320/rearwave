package com.rearwave.blog.component.utils;

import com.rearwave.blog.model.dto.AuthUserDto;
import org.apache.commons.lang3.StringUtils;
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
        AuthUserDto user = getCurrentUser();
       return user == null ? null:user.getUserId();
    }


    public static AuthUserDto getCurrentUser(){
        String token = getCurrentRequest().getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String user = RedisUtil.get(token);
        return null == user ? null
                :GSON.parseObject(user,AuthUserDto.class);
    }


    public static String getSessionId(){
        return getCurrentRequest().getSession().getId();
    }
}
