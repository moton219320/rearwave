package com.rearwave.blog.admin.model.dto;

import com.rearwave.blog.admin.model.Dic;
import com.rearwave.blog.model.Page;
import lombok.Data;

/**
 * @author Sweet Mi
 */
@Data
public class DicQueryDto extends Page<Dic> {

    private String dicName;

    private String value;
}
