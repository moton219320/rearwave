package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Tags;
import com.rearwave.blog.admin.dao.TagsMapper;
import com.rearwave.blog.admin.service.ITagsService;
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
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

}
