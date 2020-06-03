package com.rearwave.blog.component.annotation;

import com.rearwave.blog.component.enums.PlatformEnum;

import java.lang.annotation.*;

/**
 * 自动签名标记注解
 * @author sunyi
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptBody {

    PlatformEnum value() default PlatformEnum.BLOG;
}
