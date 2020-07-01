package com.rearwave.blog.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rearwave.blog.admin.dao.SocialConfigMapper;
import com.rearwave.blog.admin.model.SocialConfig;
import com.rearwave.blog.admin.model.dto.SocialConfDto;
import com.rearwave.blog.admin.service.ISocialConfigService;
import com.rearwave.blog.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表社交信息表 服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class SocialConfigServiceImpl extends ServiceImpl<SocialConfigMapper, SocialConfig> implements ISocialConfigService {

    @Override
    public Page<SocialConfig> selectPage(SocialConfDto query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        Wrapper<SocialConfig> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(query.getType())){
            wrapper.eq("type",query.getType());
        }
        if(null!=query.getEnable()){
            wrapper.eq("enable",query.getEnable());
        }
        List<SocialConfig> configs = selectList(wrapper);
        query.setRows(configs);
        PageInfo<SocialConfig> info = new PageInfo<>(configs);
        query.setTotal(info.getTotal());
        return query;
    }
}
