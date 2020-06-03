package com.rearwave.platform.baidu.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 图文内容
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends BaiduBaseDTO {

    private String title;


    private String content;

    private String originUrl;

    @SerializedName("cover_images")
    private List<ImageInfo> coverImages;
    @SerializedName("is_original")
    private Integer isOriginal ;
}
