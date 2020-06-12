package com.rearwave.blog.config.interceptor;

import com.rearwave.blog.component.response.CodeEnum;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.component.utils.GSON;
import com.rearwave.blog.component.utils.RedisUtil;
import com.rearwave.blog.component.utils.SNUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 管理端登录权限拦截器
 * @author sunyi
 */
@Log4j2
public class RequestMethodInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        SNUtil.setUseTime(System.currentTimeMillis());
        SNUtil.set(System.nanoTime());
        log.info("RWB_IN [\"{}\"] receive << [param:{},sn:{}",uri,getParamString(request),SNUtil.get());
        String token = request.getHeader("token");
        String userStr = RedisUtil.get(token);
        if (StringUtils.isBlank(userStr)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.println(GSON.toJSONString(R.error(CodeEnum.TIME_OUT)));
            return false;
        }
        return true;
    }

    private String getParamString(HttpServletRequest request){
        String result = null;
        String contentType = request.getContentType();
        if (contentType.contains("json")){
            //应对 application/json的情况
//            try {
//                result = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//            } catch (IOException e) {
                result = GSON.toJSONString(request.getParameterMap());
//            }
        } else {
            result = GSON.toJSONString(request.getParameterMap());
        }

        return result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("RWB_OUT [\"{}\"] return >> [results:{},sn:{}] exec_time:{} ms"
                ,request.getRequestURI(),modelAndView,SNUtil.get(),System.currentTimeMillis() - SNUtil.getUseTime());
    }


}
