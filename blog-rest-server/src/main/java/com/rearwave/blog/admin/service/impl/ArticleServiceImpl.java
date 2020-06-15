package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Article;
import com.rearwave.blog.admin.dao.ArticleMapper;
import com.rearwave.blog.admin.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Log4j2
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public boolean saveArticle(Article article) {
        boolean flag = insertOrUpdate(article);
        //判断是否需要同步
        int sync = article.getSyncStatus();
        if (1==sync){
            
        }
        return false;
    }
}
