package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Category;
import com.rearwave.blog.admin.model.dto.CategoryQueryDto;
import com.rearwave.blog.admin.service.ICategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/save")
    public Object add(@RequestBody Category category){
        return R.success(category.insertOrUpdate());
    }

    @GetMapping("/view/{id}")
    public Object get(@PathVariable String id){
        return R.success(categoryService.selectById(id));
    }

    @PostMapping("/query")
    public Object query(@RequestBody CategoryQueryDto query){
        return R.success(categoryService.selectPage(query));
    }

    @GetMapping("/del/{id}")
    public Object del(@PathVariable String id){
        return R.success(categoryService.deleteById(id));
    }

}

