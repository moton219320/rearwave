package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Config;
import com.rearwave.blog.admin.service.IConfigService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统配置表 前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private IConfigService configService;


    @PostMapping("save")
    public Object save(@RequestBody Config config){
        return R.success(configService.insertOrUpdate(config));
    }
}

