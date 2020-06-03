package com.rearwave.blog.admin.model.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_user")
public class User extends BaseModel{

    @TableId
    private Integer id;

    private String username;

    @TableField("social_account")
    private String socialAccount;

    @TableField("nike_name")
    private String nikeName;

    private String password;

    private String channel;

    private String avatar;

    private Integer role;

    private String email;

    private String phone;

    private String introduction;

    private Integer status;

    private String accessKey;

    private String secretKey;

}
