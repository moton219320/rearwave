package com.rearwave.platform.baidu.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class Reply {

    private Integer favor;

    @SerializedName("is_fans")
    private boolean isFans;
    @SerializedName("thread_id")
    private String threadId;
    @SerializedName("reply_id")
    private String replyId;

    private String uname;

    private String avatar;
    @SerializedName("create_time")
    private String createTime;

    private String content;
    @SerializedName("like_count")
    private Integer likeCount;
    @SerializedName("reply_count")
    private Integer replyCount;
    @SerializedName("reply_to_uname")
    private String replyToUname;
    @SerializedName("reply_to_content")
    private String replyToContent;


}
