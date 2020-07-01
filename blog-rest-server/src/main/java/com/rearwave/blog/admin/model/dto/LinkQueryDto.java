package com.rearwave.blog.admin.model.dto;

import com.rearwave.blog.admin.model.Link;
import com.rearwave.blog.model.Page;
import lombok.Data;

/**
 * @author sunyi
 */
@Data
public class LinkQueryDto extends Page<Link> {

    private String title;

    private String name;

    private String description;
}
