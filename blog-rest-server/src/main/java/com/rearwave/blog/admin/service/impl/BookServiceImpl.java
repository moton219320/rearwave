package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Book;
import com.rearwave.blog.admin.dao.BookMapper;
import com.rearwave.blog.admin.service.IBookService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
