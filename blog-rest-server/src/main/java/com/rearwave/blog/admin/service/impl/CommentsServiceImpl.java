package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Comments;
import com.rearwave.blog.admin.dao.CommentsMapper;
import com.rearwave.blog.admin.service.ICommentsService;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}
