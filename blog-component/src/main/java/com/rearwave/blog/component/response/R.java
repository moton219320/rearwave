package com.rearwave.blog.component.response;

import com.rearwave.blog.component.utils.GSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author sunyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {

    private int code;

    private String msg;

    private Object data;

    public static R success(){
        return new R(CodeEnum.OK);
    }

    public static  R success(Object data){
        R r = new R(CodeEnum.OK);
        r.setData(data);
        return r;
    }

    public R push(String key,String value){
        var data = this.data;
        Map m = GSON.convert(data, Map.class);
        m.put(key,value);
        this.data = m;
        return this;
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
