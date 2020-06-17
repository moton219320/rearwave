package com.rearwave.blog.admin.model.dto;

import com.rearwave.blog.admin.model.Article;
import com.rearwave.blog.model.Page;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Sweet Mi
 */
@Data
public class ArticleQueryDto extends Page<Article> {

    private String title;

    private String category;

    private String tags;

    private String status;

    private List<Date> date;
}
