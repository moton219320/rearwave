package com.rearwave.platform.baidu.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 图集
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GalleryDTO extends BaiduBaseDTO {

    private String title;

    private String originUrl;

    @SerializedName("public_time")
    private Long publishTime;

    private List<ImageInfo> photograph;
}
