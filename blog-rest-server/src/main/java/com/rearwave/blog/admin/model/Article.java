package com.rearwave.blog.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@TableName("t_rearwave_article")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章类型 1 图文 2 视频 3 图集 4 碎语
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容 type为1时是图文内容，2时是视频地址，3时是图集的地址数组，4时是碎语内容
     */
    private String content;
    /**
     * 是否原创 0 不是  1 是
     */
    private Integer isOriginal;
    /**
     * 摘要
     */
    private String abstractText;
    /**
     * 状态 0 草稿 1 发布
     */
    private Integer status;
    /**
     * 同步状态 0 未同步 1 已同步
     */
    private Integer syncStatus;
    /**
     * 文章分类 对应分类表的id
     */
    private Integer category;
    /**
     * 文章标签 格式如 1,2,3
     */
    private String tags;
    /**
     * 封面图，对应封面图的id 格式如 1,2,3
     */
    private String coverIamges;
    /**
     * 阅读次数
     */
    private Integer viewCount;
    /**
     * 点赞次数
     */
    private Integer likeCount;
    /**
     * 评论次数
     */
    private Integer replyCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人id
     */
    private Integer createUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人id
     */
    private Integer updateUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Integer isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCoverIamges() {
        return coverIamges;
    }

    public void setCoverIamges(String coverIamges) {
        this.coverIamges = coverIamges;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Article{" +
        ", id=" + id +
        ", type=" + type +
        ", title=" + title +
        ", content=" + content +
        ", isOriginal=" + isOriginal +
        ", abstractText=" + abstractText +
        ", status=" + status +
        ", syncStatus=" + syncStatus +
        ", category=" + category +
        ", tags=" + tags +
        ", coverIamges=" + coverIamges +
        ", viewCount=" + viewCount +
        ", likeCount=" + likeCount +
        ", replyCount=" + replyCount +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
