package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.Tags;
import com.baomidou.mybatisplus.service.IService;
import com.rearwave.blog.model.Page;
import com.rearwave.blog.model.dto.TagsQueryDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface ITagsService extends IService<Tags> {


    Page<Tags> selectPage(TagsQueryDto query);
}
