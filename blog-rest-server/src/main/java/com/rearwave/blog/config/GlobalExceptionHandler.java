package com.rearwave.blog.config;

import com.rearwave.blog.component.exception.GlobalException;
import com.rearwave.blog.component.response.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理，以正常的JSON内容格式返回错误信息
 * @author sunyi
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${debug:false}")
    private Boolean debug;

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
        log.error("系统异常",e);
        if(debug){
            return R.error(e.getMessage());
        }
        return R.error("系统出错！");
    }

    @ExceptionHandler(GlobalException.class)
    public Object handlerGlobalException(GlobalException e){
        log.error("业务异常",e);
        return e.getR();
    }
}
