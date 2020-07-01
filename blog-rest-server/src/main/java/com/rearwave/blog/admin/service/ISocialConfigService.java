package com.rearwave.blog.admin.service;

import com.rearwave.blog.admin.model.SocialConfig;
import com.baomidou.mybatisplus.service.IService;
import com.rearwave.blog.admin.model.dto.SocialConfDto;
import com.rearwave.blog.model.Page;

/**
 * <p>
 * 用户表社交信息表 服务类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
public interface ISocialConfigService extends IService<SocialConfig> {


    Page<SocialConfig> selectPage(SocialConfDto dto);
}
