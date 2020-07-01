package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.SocialConfig;
import com.rearwave.blog.admin.model.dto.SocialConfDto;
import com.rearwave.blog.admin.service.ISocialConfigService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表社交信息表 前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/socialConfig")
public class SocialConfigController {

    @Autowired
    private ISocialConfigService socialConfigService;

    @PostMapping("/save")
    public Object save(@RequestBody SocialConfig socialConfig){
        return R.success(socialConfigService.insertOrUpdate(socialConfig));
    }

    @GetMapping("/enable/{id}")
    public Object enable(@PathVariable Integer id){
        SocialConfig config = socialConfigService.selectById(id);
        config.setEnable(1-config.getEnable());
        return R.success(config.updateById());
    }

    @GetMapping("/view/{id}")
    public Object view(@PathVariable Integer id){
        return R.success(socialConfigService.selectById(id));
    }


    @PostMapping("/query")
    public Object query(@RequestBody SocialConfDto dto){
        return R.success(socialConfigService.selectPage(dto));
    }

}

