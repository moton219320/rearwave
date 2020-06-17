package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.rearwave.blog.admin.dao.UsersMapper;
import com.rearwave.blog.admin.model.Users;
import com.rearwave.blog.admin.model.dto.ForgotDto;
import com.rearwave.blog.admin.service.IUsersService;
import com.rearwave.blog.component.exception.GlobalException;
import com.rearwave.blog.component.spring.service.EmailService;
import com.rearwave.blog.component.utils.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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

    @Autowired
    private Producer producer;

    @Override
    public String validMail(String mail) {
        Assert.notNull(mail,"邮箱地址不能为空！");
        String code = producer.createText();
        emailService.sendTextMail(mail,"邮箱校验","您的验证码为【"+code+"】请于3分钟内使用，过期请重新获取。");
        RedisUtil.setEx("valid:"+mail,code,3,TimeUnit.MINUTES);
        return null;
    }

    @Override
    public String forgotPass(ForgotDto forgotDto) {

        //校验邮箱验证码
        String code = RedisUtil.get("valid:"+forgotDto.getEmail());
        if (!StringUtils.equals(code,forgotDto.getValidCode())){
            throw new GlobalException("邮箱验证码不正确！");
        }
        //先找到用户信息
        Wrapper<Users> wrapper = new EntityWrapper<>();
        wrapper.eq("email",forgotDto.getEmail());
        Users user = selectOne(wrapper);
        if (null == user) {
            throw new GlobalException("用户信息不存在！");
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
            emailService.sendHtmlMail(user.getEmail(),"重置密码",template);
        } catch (IOException e) {
            log.error("读取邮件模板异常",e);
            throw new GlobalException(e);
        }

        return resetPasswordUrl;
    }
}
