package com.rearwave.blog.model.dto;

import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class ForgotDto {

    private String username;

    private String phone;

    private String email;
}
