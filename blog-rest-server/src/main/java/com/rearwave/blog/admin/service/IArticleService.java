package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.Article;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface IArticleService extends IService<Article> {

    boolean saveArticle(Article article);

}
