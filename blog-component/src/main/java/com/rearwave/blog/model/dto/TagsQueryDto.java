package com.rearwave.blog.model.dto;

import com.rearwave.blog.model.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagsQueryDto extends Page {

    private String tagName;
}
