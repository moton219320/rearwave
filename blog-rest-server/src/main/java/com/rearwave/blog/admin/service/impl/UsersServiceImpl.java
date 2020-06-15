package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rearwave.blog.admin.model.Users;
import com.rearwave.blog.admin.dao.UsersMapper;
import com.rearwave.blog.admin.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rearwave.blog.component.exception.GlobalException;
import com.rearwave.blog.component.spring.service.EmailService;
import com.rearwave.blog.component.utils.RedisUtil;
import com.rearwave.blog.model.dto.ForgotDto;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
@Log4j2
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Value("${rearwave.admin.baseDomain}")
    private String baseDomain;

    @Autowired
    private EmailService emailService;
    @Override
    public String forgotPass(ForgotDto forgotDto) {
        //先找到用户信息
        Wrapper<Users> wrapper = new EntityWrapper<>();
        wrapper.eq("username",forgotDto.getUsername());
        Users user = selectOne(wrapper);
        if (null == user || !StringUtils.equals(user.getEmail(),forgotDto.getEmail())) {
            throw new GlobalException("用户名或邮箱地址不正确");
        }
        String key = Base64Utils.encodeToString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        //有效期3天
        RedisUtil.setEx("forgot:"+key,user.getId().toString(),3, TimeUnit.DAYS);

        String resetPasswordUrl = baseDomain+"#/reset/"+key;

        try {
            String template = StreamUtils.copyToString(this.getClass().getClassLoader()
                    .getResourceAsStream("static/mailTemplate.html"),StandardCharsets.UTF_8);
            //替换subject
            template = template.replace("${subject}","重置密码");
            //替换URL
            template = template.replaceAll("\\$\\{url}",resetPasswordUrl);

        } catch (IOException e) {
            log.error("读取邮件模板异常",e);
            throw new GlobalException(e);
        }

        return resetPasswordUrl;
    }
}
