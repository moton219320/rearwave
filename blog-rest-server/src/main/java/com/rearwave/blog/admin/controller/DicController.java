package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Dic;
import com.rearwave.blog.admin.model.dto.DicQueryDto;
import com.rearwave.blog.admin.service.IDicService;
import com.rearwave.blog.component.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Object query(@RequestBody DicQueryDto queryDto){
        return R.success(dicService.selectPage(queryDto));
    }

    @PostMapping("/save")
    public Object save(@RequestBody Dic dic){
        return R.success(dic.insertOrUpdate());
    }

    @GetMapping("/enable/{id}")
    public Object enable(@PathVariable Integer id){
        Dic dic = dicService.selectById(id);
        dic.setStatus(1-dic.getStatus());
        return R.success(dicService.updateById(dic));
    }

}

