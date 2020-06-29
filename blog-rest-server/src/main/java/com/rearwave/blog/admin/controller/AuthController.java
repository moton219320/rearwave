package com.rearwave.blog.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.code.kaptcha.Producer;
import com.rearwave.blog.admin.model.Users;
import com.rearwave.blog.admin.model.dto.ForgotDto;
import com.rearwave.blog.admin.model.dto.LoginDto;
import com.rearwave.blog.admin.model.vo.VerifyCodeVo;
import com.rearwave.blog.admin.service.IUsersService;
import com.rearwave.blog.component.annotation.DecryptBody;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.component.utils.EncryptUtil;
import com.rearwave.blog.component.utils.GSON;
import com.rearwave.blog.component.utils.RedisUtil;
import com.rearwave.blog.model.dto.AuthUserDto;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录控制器
 * @author sunyi
 */
@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUsersService usersService;
    @Autowired
    private Producer producer;

    @PostMapping("/login")
    public Object login(@DecryptBody LoginDto login){
        /* 先校验验证码 */
        String code = RedisUtil.get("verify:"+login.getToken());
        if (!StringUtils.equals(code,login.getVerifyCode())){
            return R.error("验证码不正确！");
        }
        /* 校验用户密码 */
        Wrapper<Users> wrapper = new EntityWrapper<>();
        wrapper.eq("username",login.getUsername());
        Users user = usersService.selectOne(wrapper);
        if (null == user ||
                !user.getPassword().equals(EncryptUtil.encryptPassword(login.getPassword(),user.getUsername()))){
            return R.error("用户名或密码不正确");
        }
        if (1==user.getStatus()){
            return R.error("账户已被锁定，请联系站长解除锁定");
        }
        String token = Base64Utils.encodeToString(DigestUtils.md5Digest(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
        AuthUserDto userDto = new AuthUserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setUserId(user.getId());
        userDto.setToken(token);
        //保存token信息 ,有效期 7天
        RedisUtil.setEx(token, GSON.toJSONStringWithNullValue(userDto),7, TimeUnit.DAYS);
        return R.success(userDto);
    }

    /**
     * 获取验证码
     * @return base64加密后的图片内容
     * @throws IOException
     */
    @GetMapping("/getVerifyCode")
    public Object getCode() throws IOException {
        String code = producer.createText();
        String token = DigestUtils.md5DigestAsHex(code.getBytes());
        //保存验证码
        RedisUtil.setEx("verify:"+token,code,3,TimeUnit.MINUTES);

        BufferedImage img = producer.createImage(code);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(img,"png",bos);
        String pngBase64 = Base64Utils.encodeToString(bos.toByteArray());
        pngBase64 = pngBase64.replaceAll("\n","").replaceAll("\r","");

        return R.success(new VerifyCodeVo(token,"data:image/png;base64,"+pngBase64));
    }


    /**
     * 校验邮箱验证码并发送重置密码链接到邮箱
      * @param forgot
     * @return
     */
    @PostMapping("/forgot")
    public Object forgotPass(@DecryptBody ForgotDto forgot){
        //验证用户存不存在，并向用户的邮箱发送重置密码的邮件
        return R.success(usersService.forgotPass(forgot));
    }

    /**
     * 校验邮箱有效性，并发送邮箱验证码
     * @param email 邮箱地址
     * @return
     */
    @GetMapping("/validMail")
    public Object validMail(String email){
        return R.success(usersService.validMail(email));
    }

  /*  @Autowired
    private EmailService emailService;
    @GetMapping("sendMail")
    public Object testMail(){

        try {
            String template = StreamUtils.copyToString(this.getClass().getClassLoader()
                    .getResourceAsStream("static/mailTemplate.html"),StandardCharsets.UTF_8);
            //替换subject
            template = template.replace("${subject}","重置密码");
            //替换URL
            template = template.replaceAll("\\$\\{url}","http://www.rearwave.com/#/reset/eshdoii0oash9gh928389t903woisdhvoia");
            log.debug("html邮件内容 - {}",template);
            emailService.sendHtmlMail("sun219320@gmail.com","重置密码",template);
        } catch (IOException e) {
            log.error("读取邮件模板异常",e);
            throw new GlobalException(e);
        }
        return R.success();
    }*/
}
