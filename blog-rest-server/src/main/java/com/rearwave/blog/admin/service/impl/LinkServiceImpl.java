package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Link;
import com.rearwave.blog.admin.dao.LinkMapper;
import com.rearwave.blog.admin.service.ILinkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
