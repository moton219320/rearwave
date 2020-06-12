package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.BookNote;
import com.rearwave.blog.admin.dao.BookNoteMapper;
import com.rearwave.blog.admin.service.IBookNoteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书笔记表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements IBookNoteService {

}
