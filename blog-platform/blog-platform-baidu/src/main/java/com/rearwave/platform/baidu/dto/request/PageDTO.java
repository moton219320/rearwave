package com.rearwave.platform.baidu.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDTO extends BaiduBaseDTO {

    @SerializedName("page_no")
    private Integer pageNo;
    @SerializedName("page_size")
    private Integer pageSize;
}
