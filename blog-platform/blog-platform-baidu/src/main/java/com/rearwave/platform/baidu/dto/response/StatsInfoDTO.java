package com.rearwave.platform.baidu.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 文章实时数据
 * @author sunyi
 */
@Data
public class StatsInfoDTO {

    /**
     * -comment_count	int	评论量，返回-1表示获取失败
     * -view_count	int	阅读/播放量，返回-1表示获取失败
     * -share_count	int	分享量，返回-1表示获取失败
     * -collect_count	int	收藏量，返回-1表示获取失败
     * -likes_count	int	点赞量，返回-1表示获取失败
     * -recommend_count	int	推荐量，返回-1表示获取失败
     */
    @SerializedName("recommend_count")
    private Integer recommendCount;

    @SerializedName("comment_count")
    private Integer commentCount;

    @SerializedName("view_count")
    private Integer viewCount;

    @SerializedName("share_count")
    private Integer shareCount;

    @SerializedName("collect_count")
    private Integer collectCount;

    @SerializedName("likes_count")
    private Integer likesCount;
}
