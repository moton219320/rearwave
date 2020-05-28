package com.rearwave.blog.component.annotation;

import java.lang.annotation.*;

/**
 * 自动签名标记注解
 * @author sunyi
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptBody {
}
