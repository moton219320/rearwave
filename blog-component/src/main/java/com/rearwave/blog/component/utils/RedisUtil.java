package com.rearwave.blog.component.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author sunyi
 */
public final class RedisUtil {

    private static final StringRedisTemplate template = SpringUtil.getBean(StringRedisTemplate.class);

    public static String get(String key){
        return template.opsForValue().get(key);
    }
}
