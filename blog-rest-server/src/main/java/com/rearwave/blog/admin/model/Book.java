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
 * 图书表
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@TableName("t_rearwave_book")
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 书名
     */
    private String title;
    /**
     * 封面
     */
    private String coverImage;
    /**
     * 作者
     */
    private String author;
    /**
     * 分类类别
     */
    private String categoryId;
    /**
     * 是否推荐
     */
    private Integer recommend;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 出版日期
     */
    private Date publishDate;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 评分
     */
    private Double grade;
    /**
     * 简介
     */
    private String description;
    /**
     * 原书目录
     */
    private String catalogue;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 阅读量
     */
    private Integer readCount;
    /**
     * 评论量
     */
    private Integer replyCount;
    /**
     * 点赞量
     */
    private Integer likeCount;
    /**
     * 是否发布
     */
    private Integer publish;
    /**
     * 读书状态
     */
    private Integer progress;
    /**
     * 是否阅读
     */
    private Integer reading;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Book{" +
        ", id=" + id +
        ", title=" + title +
        ", coverImage=" + coverImage +
        ", author=" + author +
        ", categoryId=" + categoryId +
        ", recommend=" + recommend +
        ", publisher=" + publisher +
        ", publishDate=" + publishDate +
        ", pageNum=" + pageNum +
        ", grade=" + grade +
        ", description=" + description +
        ", catalogue=" + catalogue +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", readCount=" + readCount +
        ", replyCount=" + replyCount +
        ", likeCount=" + likeCount +
        ", publish=" + publish +
        ", progress=" + progress +
        ", reading=" + reading +
        "}";
    }
}
