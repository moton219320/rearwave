package com.rearwave.blog.component.exception;

import com.rearwave.blog.component.response.CodeEnum;
import com.rearwave.blog.component.response.R;
import lombok.Getter;

/**
 * 通用异常类
 * @author sunyi
 */
@Getter
public class GlobalException extends RuntimeException {

    private R r;

    public GlobalException(String message) {
        super(message);
        this.r = R.error(message);
    }

    public GlobalException(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.r = R.error(codeEnum);
    }
}
