package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Article;
import com.rearwave.blog.admin.model.dto.ArticleQueryDto;
import com.rearwave.blog.admin.service.IArticleService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @PostMapping("/save")
    public Object save(@RequestBody Article article){
        return R.success(articleService.saveArticle(article));
    }

    @PostMapping("/query")
    public Object query(@RequestBody ArticleQueryDto queryDto){
        return R.success(articleService.selectPage(queryDto));
    }

}

