package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rearwave.blog.admin.dao.ArticleMapper;
import com.rearwave.blog.admin.model.Article;
import com.rearwave.blog.admin.model.dto.ArticleQueryDto;
import com.rearwave.blog.admin.service.IArticleService;
import com.rearwave.blog.model.Page;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public Page<Article> selectPage(ArticleQueryDto query) {

        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        var wrapper = new EntityWrapper<Article>()
                .like("title",query.getTitle());
        if (StringUtils.isNotBlank(query.getCategory())){
            wrapper.eq("category",query.getCategory());
        }
        if (StringUtils.isNotBlank(query.getStatus())){
            wrapper.eq("status",query.getStatus());
        }
        if (!query.getDate().isEmpty()){
            wrapper.ge("create_time",query.getDate().get(0))
                    .lt("create_time",DateUtils.addDays(query.getDate().get(1),1));
        }
        if(!query.getTags().isBlank()){
            wrapper.like("tags",query.getTags()+",", SqlLike.LEFT)
                    .or()
                    .like("tags",","+query.getTags()+",")
                    .or().like("tags",","+query.getTags(),SqlLike.RIGHT);
        }


        List<Article> articles = selectList(wrapper);
        query.setRows(articles);
        PageInfo<Article> info = new PageInfo<>(articles);
        query.setTotal(info.getTotal());
        return query;
    }

    public static void main(String[] args) {
        ArticleQueryDto query = new ArticleQueryDto();
        query.setCategory("1");
        query.setDate(List.of(new Date(),new Date()));
        query.setStatus("1");
        query.setTags("3");
        query.setTitle("测试");
        var wrapper = new EntityWrapper<Article>()
                .like("title",query.getTitle());
        if (StringUtils.isNotBlank(query.getCategory())){
            wrapper.eq("category",query.getCategory());
        }
        if (StringUtils.isNoneBlank(query.getStatus())){
            wrapper.eq("status",query.getStatus());
        }
        if (!query.getDate().isEmpty()){
            wrapper.ge("create_time",query.getDate().get(0))
                    .lt("create_time",DateUtils.addDays(query.getDate().get(1),1));
        }
        if(!query.getTags().isBlank()){
            wrapper.like("tags",query.getTags()+",", SqlLike.LEFT)
                    .or()
                    .like("tags",","+query.getTags()+",")
                    .or().like("tags",","+query.getTags(),SqlLike.RIGHT);
        }

        System.out.println(wrapper.getSqlSegment());
    }
}
