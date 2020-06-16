package com.rearwave.blog.model.dto;

import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class LoginDto {

    private String username;

    private String password;

    private String verifyCode;

    private String token;
}
