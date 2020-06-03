package com.rearwave.platform.baidu.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class Dict {

    @SerializedName("article_id")
    private String articleId;

    private String nid;
}
