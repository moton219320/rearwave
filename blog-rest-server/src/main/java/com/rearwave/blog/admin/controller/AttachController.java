package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Attach;
import com.rearwave.blog.admin.service.IAttachService;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.component.utils.WebUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
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
@DependsOn({"rearWaveApplicationConfigure"})
public class AttachController {

    @Value("${uploadDir}")
    private String uploadDir;
    @Autowired
    private IAttachService attachService;

    @SneakyThrows
    @PostMapping("upload")
    public Object uplaod(@RequestParam MultipartFile file){
        if (file.isEmpty()){
            return R.error("文件上传失败");
        }
        String filename = file.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf(".")+1);
        String name = DigestUtils.md5DigestAsHex(file.getBytes());
        Integer userId = WebUtils.getUserId();
        String path = String.join("/",userId.toString(), DateFormatUtils.format(new Date(),"yyyyMMdd"));
        File dir = new File(String.join("/",uploadDir,path));
        if (!dir.exists()){
            dir.mkdirs();
        }
        file.transferTo(new File(String.join("/",uploadDir,path,name+"."+ext)));
        Attach attach = new Attach();
        attach.setFilename(filename);
        attach.setFilePath(path);
        attach.setFileSize(Math.toIntExact(file.getSize()));
        attach.setFileType(ext);
        attach.setPlatform(0);
        attach.setCreateUser(userId);
        attach.setCreateTime(new Date());
        attachService.insert(attach);
        return R.success(attach);
    }
}

