package com.rearwave.blog.component.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    private int code;

    private String msg;

    private T data;

    public static R success(){
        return new R(CodeEnum.OK);
    }

    public static <T> R<T> success(T data){
        R<T> r = new R<>(CodeEnum.OK);
        r.setData(data);
        return r;
    }

    private R(CodeEnum codeEnum){
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public static R error(CodeEnum codeEnum){
        return new R(codeEnum);
    }

    public static R error(String msg){
        return new R(CodeEnum.FAIL).format(msg);
    }

    private R format(String msg){
        this.msg = String.format(this.msg,msg);
        return this;
    }



}
