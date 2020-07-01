package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Attach;
import com.rearwave.blog.admin.service.IAttachService;
import com.rearwave.blog.component.exception.GlobalException;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.component.utils.SpringUtil;
import com.rearwave.blog.component.utils.WebUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/attach")
public class AttachController {

    @Autowired
    private IAttachService attachService;

    @SneakyThrows
    @PostMapping("/image")
    public Object uplaod(@RequestParam MultipartFile file){


        return R.success(saveFile(SpringUtil.getProperty("upload.local.image"),file));
    }

    @SneakyThrows
    private Attach saveFile(String directory,MultipartFile file){
        if (file.isEmpty()){
            throw new GlobalException("上传文件失败");
        }

        String filename = file.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf(".")+1);
        String name = DigestUtils.md5DigestAsHex(file.getBytes());
        Integer userId = WebUtils.getUserId();
        String path = String.join("/",userId.toString(), DateFormatUtils.format(new Date(),"yyyyMMdd"));
        File dir = new File(String.join("/",directory,path));
        if (!dir.exists()){
            dir.mkdirs();
        }
        file.transferTo(new File(String.join("/",directory,path,name+"."+ext)));
        Attach attach = new Attach();
        attach.setFilename(filename);
        attach.setFilePath(path);
        attach.setFileSize(Math.toIntExact(file.getSize()));
        attach.setFileType(ext);
        attach.setPlatform(0);
        attach.setCreateUser(userId);
        attach.setCreateTime(new Date());
        attachService.insert(attach);
        return attach;
    }
    @PostMapping("video")
    public Object uploadVideo(@RequestParam MultipartFile file) {

        return R.success(saveFile(SpringUtil.getProperty("upload.local.video"),file));
    }

    @PostMapping("file")
    public Object uploadFile(@RequestParam MultipartFile file){
        return R.success(saveFile(SpringUtil.getProperty("upload.local.file"),file));
    }
}

