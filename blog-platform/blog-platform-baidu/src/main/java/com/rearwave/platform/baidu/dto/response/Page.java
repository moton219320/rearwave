package com.rearwave.platform.baidu.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class Page {

    /**
     * --page_no	int	查询页码
     * --page_size	int	查询条数
     * --has_next	int	是否有下一页
     * --items_count	int	查询到的评论条数
     */
    @SerializedName("page_no")
    private Integer pageNo;

    @SerializedName("page_size")
    private Integer pageSize;

    @SerializedName("has_next")
    private Boolean hasNext;

    @SerializedName("items_count")
    private Integer itemsCount;
}
