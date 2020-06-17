package com.rearwave.blog.admin.model.dto;

import com.rearwave.blog.admin.model.Category;
import com.rearwave.blog.model.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sunyi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryQueryDto extends Page<Category> {

    private String name;
}
