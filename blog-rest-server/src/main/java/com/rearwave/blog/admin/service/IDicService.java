package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.Dic;
import com.baomidou.mybatisplus.service.IService;
import com.rearwave.blog.admin.model.dto.DicQueryDto;
import com.rearwave.blog.model.Page;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface IDicService extends IService<Dic> {


    Page<Dic> selectPage(DicQueryDto queryDto);
}
