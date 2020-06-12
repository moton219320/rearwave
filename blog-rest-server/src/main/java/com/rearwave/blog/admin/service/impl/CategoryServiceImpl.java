package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Category;
import com.rearwave.blog.admin.dao.CategoryMapper;
import com.rearwave.blog.admin.service.ICategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
