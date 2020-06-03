package com.rearwave.platform.baidu.dto.response;

import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class RespDTO<T> {

    private Integer errno;

    private String errmsg;

    private T data;
}
