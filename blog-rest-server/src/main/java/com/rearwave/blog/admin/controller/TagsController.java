package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Tags;
import com.rearwave.blog.admin.service.ITagsService;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.model.dto.TagsQueryDto;
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
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private ITagsService tagsService;

    @PostMapping("/save")
    public Object add(@RequestBody Tags tags){
        return R.success(tags.insertOrUpdate());
    }

    @GetMapping("/view/{id}")
    public Object get(@PathVariable String id){
        return R.success(tagsService.selectById(id));
    }

    @PostMapping("/query")
    public Object query(@RequestBody TagsQueryDto query){
        return R.success(tagsService.selectPage(query));
    }
}

