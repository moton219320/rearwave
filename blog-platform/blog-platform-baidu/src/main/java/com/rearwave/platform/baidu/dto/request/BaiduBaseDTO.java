package com.rearwave.platform.baidu.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 百家号 通用模型
 * @author sunyi
 */
@Data
public class BaiduBaseDTO {

    /**
     * 应用id
     */
    @SerializedName(value = "app_id")
    private String appId;
    /**
     * 应用令牌
     */
    @SerializedName(value = "app_token")
    private String appToken;

    @SerializedName("article_id")
    private String articleId;
}
