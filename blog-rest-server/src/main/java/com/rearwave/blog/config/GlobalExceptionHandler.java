package com.rearwave.blog.config;

import com.rearwave.blog.component.exception.GlobalException;
import com.rearwave.blog.component.response.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理，以正常的JSON内容格式返回错误信息
 * @author sunyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
        return R.error(e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public Object handlerGlobalException(GlobalException e){
        return e.getR();
    }
}
