package com.rearwave.platform.baidu.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoDTO extends BaiduBaseDTO{

    private String title;

    @SerializedName("video_url")
    private String videoUrl;

    private String tag;
    @SerializedName("cover_images")
    private String coverImages;
    @SerializedName("is_original")
    private Integer isOriginal;
    @SerializedName("use_auto_cover")
    private Integer useAutoCover;
}
