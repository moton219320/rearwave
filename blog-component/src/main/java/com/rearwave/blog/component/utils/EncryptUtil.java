package com.rearwave.blog.component.utils;

import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 加密工具
 * @author sunyi
 */
public final class EncryptUtil {


    /**
     * 对密码进行加密，不可逆
     * @param password 明文密码
     * @param salt 盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String password,String salt){

        /* 先将密码通过md5加密一次，在加上盐再加密一次 */
        String encPd = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        return DigestUtils.md5DigestAsHex((encPd + salt).getBytes(StandardCharsets.UTF_8));
    }


    public static void main(String[] args) {
        System.out.println(encryptPassword("123456","admin"));
        System.out.println(new String(Base64Utils.encode("{\"username\":\"admin\",\"password\":\"123456\",\"verifyCode\":\"4b8696\"}".getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8));

        System.out.println(new String(Base64Utils.decodeFromString("eyJ1c2VybmFtZSI6ImFkbWluIiwicGFzc3dvcmQiOiIxMjM0NTYiLCJ2ZXJpZnlDb2RlIjoiNGI4Njk2In0="),StandardCharsets.UTF_8));
    }
}
