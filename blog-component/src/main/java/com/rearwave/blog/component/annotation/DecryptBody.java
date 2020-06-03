package com.rearwave.blog.component.annotation;

import com.rearwave.blog.component.enums.PlatformEnum;

import java.lang.annotation.*;



/**
 * 自动解析签名注解
 * @author sunyi
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DecryptBody {

    PlatformEnum value() default PlatformEnum.BLOG;
}
