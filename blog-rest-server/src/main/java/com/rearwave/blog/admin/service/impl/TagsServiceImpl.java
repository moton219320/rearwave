package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rearwave.blog.admin.dao.TagsMapper;
import com.rearwave.blog.admin.model.Tags;
import com.rearwave.blog.admin.service.ITagsService;
import com.rearwave.blog.model.Page;
import com.rearwave.blog.admin.model.dto.TagsQueryDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

    @Override
    public Page<Tags> selectPage(TagsQueryDto query) {

        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<Tags> tags = selectList(new EntityWrapper<Tags>().like("name",query.getName()));
        query.setRows(tags);
        PageInfo<Tags> info = new PageInfo<>(tags);
        query.setTotal(info.getTotal());
        return query;
    }
}
