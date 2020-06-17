package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.Users;
import com.baomidou.mybatisplus.service.IService;
import com.rearwave.blog.admin.model.dto.ForgotDto;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface IUsersService extends IService<Users> {


    /**
     * 邮件发送重置密码地址
     * @param forgotDto 请求对象
     * @return 重置密码地址
     */
    String forgotPass(ForgotDto forgotDto);

    /**
     * 邮件发送校验码
     * @param mail 邮箱地址
     * @return
     */
    String validMail(String mail);
}
