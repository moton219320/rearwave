package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.Category;
import com.baomidou.mybatisplus.service.IService;
import com.rearwave.blog.admin.model.dto.CategoryQueryDto;
import com.rearwave.blog.model.Page;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface ICategoryService extends IService<Category> {

    Page<Category> selectPage(CategoryQueryDto queryDto);

}
