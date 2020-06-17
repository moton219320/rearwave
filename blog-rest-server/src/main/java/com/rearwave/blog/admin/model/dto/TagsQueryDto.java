package com.rearwave.blog.admin.model.dto;

import com.rearwave.blog.admin.model.Tags;
import com.rearwave.blog.model.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagsQueryDto extends Page<Tags> {

    private String name;
}
