package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Article;
import com.rearwave.blog.admin.dao.ArticleMapper;
import com.rearwave.blog.admin.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
