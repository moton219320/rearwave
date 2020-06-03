package com.rearwave.blog.admin.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @author sunyi
 */
@Data
public class BaseModel {

    private Date createTime;

    private String createUser;

    private String updateUser;

    private String updateTime;
}
