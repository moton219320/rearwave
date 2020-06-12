package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Config;
import com.rearwave.blog.admin.dao.ConfigMapper;
import com.rearwave.blog.admin.service.IConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
