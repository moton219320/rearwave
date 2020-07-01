package com.rearwave.blog.admin.model.dto;

import com.rearwave.blog.admin.model.SocialConfig;
import com.rearwave.blog.model.Page;
import lombok.Data;

/**
 * @author Sweet Mi
 */
@Data
public class SocialConfDto extends Page<SocialConfig> {

    private String type;

    private Integer enable;

}
