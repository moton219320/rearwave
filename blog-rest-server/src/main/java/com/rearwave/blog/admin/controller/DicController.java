package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Dic;
import com.rearwave.blog.admin.model.dto.DicQueryDto;
import com.rearwave.blog.admin.service.IDicService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/dic")
public class DicController {
    @Autowired
    private IDicService dicService;

    @PostMapping("/query")
    public Object Query(@RequestBody DicQueryDto queryDto){
        return R.success(dicService.selectPage(queryDto));
    }

    @PostMapping("/save")
    public Object save(@RequestBody Dic dic){
        return R.success(dic.insertOrUpdate());
    }

}

