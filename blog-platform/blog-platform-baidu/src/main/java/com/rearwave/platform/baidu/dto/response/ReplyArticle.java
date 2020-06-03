package com.rearwave.platform.baidu.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author sunyi
 */
@Data
public class ReplyArticle {

    private String title;

    @SerializedName("thread_id")
    private String threadId;

    private String type;

    @SerializedName("article_id")
    private String articleId;

    private String nid;

    private Integer total;

    @SerializedName("reply_list")
    private List<Reply> replyList;
}
