package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rearwave.blog.admin.dao.DicMapper;
import com.rearwave.blog.admin.model.Dic;
import com.rearwave.blog.admin.model.dto.DicQueryDto;
import com.rearwave.blog.admin.service.IDicService;
import com.rearwave.blog.model.Page;
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
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements IDicService {

    @Override
    public Page<Dic> selectPage(DicQueryDto query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<Dic> dics = selectList(new EntityWrapper<Dic>()
                .like("dic_name",query.getDicName())
                .like("value",query.getValue()));
        query.setRows(dics);
        PageInfo<Dic> info = new PageInfo<>(dics);
        query.setTotal(info.getTotal());
        return query;
    }
}
