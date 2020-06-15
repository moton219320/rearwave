package com.rearwave.blog.component.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sunyi
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum  PlatformEnum {

    /*百家号 */
    BAIDU,
    /*头条号*/
    TOUTIAO,
    /*企鹅号*/
    TENCENT,
    /*大鱼号*/
    DAYU,
    /*知乎*/
    ZHIHU,
    /*微信公众号*/
    WECHAT,
    /*博客*/
    BLOG,
    /** 第三方API */
    API,
    ;

}
