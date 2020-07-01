package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Link;
import com.rearwave.blog.admin.model.dto.LinkQueryDto;
import com.rearwave.blog.admin.service.ILinkService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 友情链接表 前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private ILinkService linkService;


    @PostMapping("/query")
    public Object query(@RequestBody LinkQueryDto queryDto){
        return R.success(linkService.selectPage(queryDto));
    }

    @PostMapping("/save")
    public Object save(@RequestBody Link link){
        return R.success(linkService.insertOrUpdate(link));
    }

    @GetMapping("del/{id}")
    public Object del(@PathVariable Integer id){
        return R.success(linkService.deleteById(id));
    }
}

