package com.rearwave.blog.component.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunyi
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    /**
     * 成功
     */
    OK(200,"成功"),
    /**
     * 通用失败
     */
    FAIL(999,"%s"),

    TIME_OUT(201,"登录超时"),
    ;
    private int code;

    private String msg;


}
