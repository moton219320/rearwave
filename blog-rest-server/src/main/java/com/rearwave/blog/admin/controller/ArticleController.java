package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Article;
import com.rearwave.blog.admin.model.dto.ArticleQueryDto;
import com.rearwave.blog.admin.service.IArticleService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sync/{id}")
    public Object sync(@PathVariable Integer id){
        Article prop = new Article();
        prop.setId(id);
        prop.setSyncStatus(1);
        return R.success(articleService.saveArticle(prop));
    }

    @GetMapping("/del/{id}")
    public Object del(@PathVariable Integer id){
        return R.success(articleService.deleteById(id));
    }

    @GetMapping("/view/{id}")
    public Object get(@PathVariable Integer id){
        return R.success(articleService.selectById(id));
    }
}

