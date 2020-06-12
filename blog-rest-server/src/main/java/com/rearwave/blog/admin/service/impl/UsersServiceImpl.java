package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Users;
import com.rearwave.blog.admin.dao.UsersMapper;
import com.rearwave.blog.admin.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
