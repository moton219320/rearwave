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
 * 用户表社交信息表
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@TableName("t_rearwave_social_config")
public class SocialConfig extends Model<SocialConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * qq、csdn、wechat、weibo、email等
     */
    private String type;
    /**
     * 社交内容
     */
    private String content;
    /**
     * 展示类型( 1、显示图片，2、显示账号，3、跳转链接)
     */
    private Integer showType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否启用 0 不启用 1 启用
     */
    private Integer enable;
    /**
     * 是否主页社交信息 0 不是 1 是
     */
    private Integer isHome;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SocialConfig{" +
        ", id=" + id +
        ", type=" + type +
        ", content=" + content +
        ", showType=" + showType +
        ", remark=" + remark +
        ", icon=" + icon +
        ", enable=" + enable +
        ", isHome=" + isHome +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
