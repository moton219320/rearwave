package com.rearwave.blog.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author sunyi
 */
@Data
public class AuthUserDto {

    private Integer id;

    private String username;

    private String nikename;

    private String avatar;

    private Integer status;

    private Integer reviewPermissions;

    private BigDecimal donationAmount;

    private Integer gender;
}
