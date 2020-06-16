package com.rearwave.blog.admin.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@TableName("t_rearwave_comments")
public class Comments extends Model<Comments> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复评论id
     */
    private Integer replyId;
    /**
     * 评论树 记录评论的层级 格式为评论id拼接 例如：1,3,6,11,25
     */
    private String replyTree;
    /**
     * 评论工具 如 Chrome IE Iphone Android等
     */
    private String userAgent;
    /**
     * 是不是Markdown内容 0 不是 1 是
     */
    private Integer isMarkdown;
    /**
     * 用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer createUser;
    /**
     * 评论时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyTree() {
        return replyTree;
    }

    public void setReplyTree(String replyTree) {
        this.replyTree = replyTree;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getIsMarkdown() {
        return isMarkdown;
    }

    public void setIsMarkdown(Integer isMarkdown) {
        this.isMarkdown = isMarkdown;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Comments{" +
        ", id=" + id +
        ", articleId=" + articleId +
        ", content=" + content +
        ", replyId=" + replyId +
        ", replyTree=" + replyTree +
        ", userAgent=" + userAgent +
        ", isMarkdown=" + isMarkdown +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        "}";
    }
}
