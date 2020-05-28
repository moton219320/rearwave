package com.rearwave.blog.component.annotation;

import java.lang.annotation.*;



/**
 * 自动解析签名注解
 * @author sunyi
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DecryptBody {
}
