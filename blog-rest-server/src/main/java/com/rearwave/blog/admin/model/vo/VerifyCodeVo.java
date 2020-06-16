package com.rearwave.blog.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Sweet Mi
 */
@Data
@AllArgsConstructor
public class VerifyCodeVo {

    private String token;

    private String img;
}
