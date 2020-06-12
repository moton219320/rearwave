package com.rearwave.blog.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@TableName("t_rearwave_users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id_", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 性别 0 其他 1 男 2 女
     */
    private Integer gender;
    /**
     * 昵称
     */
    private String nikename;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 来源 0 平台注册 1 微信登录 2 百度登录 3 GitHub登录 4 头条登录 5 微博登录
     */
    private Integer channel;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 0 正常 1 锁定
     */
    private Integer status;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 个人介绍
     */
    private String introduction;
    /**
     * 角色 0 普通用户 1 管理员
     */
    private Integer role;
    /**
     * 第三方社交账号id
     */
    private String socialId;
    /**
     * API调用链接key
     */
    private String accessKey;
    /**
     * 私钥
     */
    private String secretKey;
    /**
     * 评论权限 0 锁定 1 正常
     */
    private Integer reviewPermissions;
    /**
     * 捐赠金额 单位：元
     */
    private BigDecimal donationAmount;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getReviewPermissions() {
        return reviewPermissions;
    }

    public void setReviewPermissions(Integer reviewPermissions) {
        this.reviewPermissions = reviewPermissions;
    }

    public BigDecimal getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(BigDecimal donationAmount) {
        this.donationAmount = donationAmount;
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
        return "Users{" +
        ", id=" + id +
        ", username=" + username +
        ", gender=" + gender +
        ", nikename=" + nikename +
        ", password=" + password +
        ", phone=" + phone +
        ", email=" + email +
        ", channel=" + channel +
        ", lastLoginTime=" + lastLoginTime +
        ", status=" + status +
        ", avatar=" + avatar +
        ", introduction=" + introduction +
        ", role=" + role +
        ", socialId=" + socialId +
        ", accessKey=" + accessKey +
        ", secretKey=" + secretKey +
        ", reviewPermissions=" + reviewPermissions +
        ", donationAmount=" + donationAmount +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
