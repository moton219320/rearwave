package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rearwave.blog.admin.dao.CategoryMapper;
import com.rearwave.blog.admin.model.Category;
import com.rearwave.blog.admin.model.dto.CategoryQueryDto;
import com.rearwave.blog.admin.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public Page<Category> selectPage(CategoryQueryDto queryDto) {
        PageHelper.startPage(queryDto.getPageNum(),queryDto.getPageSize());
        List<Category> categories = selectList(new EntityWrapper<Category>().like("name",queryDto.getName()));
        queryDto.setRows(categories);
        PageInfo<Category> info = new PageInfo<>(categories);
        queryDto.setTotal(info.getTotal());
        return queryDto;
    }
}
