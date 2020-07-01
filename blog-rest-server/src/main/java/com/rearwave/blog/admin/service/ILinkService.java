package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.Link;
import com.baomidou.mybatisplus.service.IService;
import com.rearwave.blog.admin.model.dto.LinkQueryDto;
import com.rearwave.blog.model.Page;

/**
 * <p>
 * 友情链接表 服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface ILinkService extends IService<Link> {


    Page<Link> selectPage(LinkQueryDto queryDto);
}
