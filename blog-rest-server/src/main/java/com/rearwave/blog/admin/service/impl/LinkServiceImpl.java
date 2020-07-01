package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rearwave.blog.admin.dao.LinkMapper;
import com.rearwave.blog.admin.model.Link;
import com.rearwave.blog.admin.model.dto.LinkQueryDto;
import com.rearwave.blog.admin.service.ILinkService;
import com.rearwave.blog.model.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 友情链接表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {

    @Override
    public Page<Link> selectPage(LinkQueryDto query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<Link> links = selectList(new EntityWrapper<Link>()
                .like("title",query.getTitle())
                .like("name",query.getName()));
        query.setRows(links);
        PageInfo<Link> info = new PageInfo<>(links);
        query.setTotal(info.getTotal());
        return query;
    }
}
