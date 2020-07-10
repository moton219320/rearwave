package com.rearwave.blog.admin.controller;


import com.rearwave.blog.admin.model.Attach;
import com.rearwave.blog.admin.service.IAttachService;
import com.rearwave.blog.component.exception.GlobalException;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.component.utils.SpringUtil;
import com.rearwave.blog.component.utils.WebUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Log4j2
@RestController
@RequestMapping("/attach")
public class AttachController {

    @Autowired
    private IAttachService attachService;

    @SneakyThrows
    @PostMapping("/image")
    public Object uplaod(MultipartFile file){

        return R.success(saveFile(SpringUtil.getProperty("upload.local.file"),"images",file))
                .push("static",SpringUtil.getProperty("rearwave.static.url"));
    }

    @SneakyThrows
    private Attach saveFile(String directory,String fileType,MultipartFile file){
        if (file.isEmpty()){
            throw new GlobalException("上传文件失败");
        }
        log.debug("上传文件名称：{},文件大小：{}",file.getOriginalFilename(),file.getSize());
        String filename = file.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf(".")+1);
        String name = DigestUtils.md5DigestAsHex(file.getBytes());
        Integer userId = WebUtils.getUserId();
        String path = String.join("/",
                fileType,
                userId.toString(),
                DateFormatUtils.format(new Date(),"yyyyMMdd"));
        File dir = new File(String.join("/",directory,path));
        if (!dir.exists()){
            dir.mkdirs();
        }
        String encName = name+"."+ext;
        File local = new File(dir,encName);
        IOUtils.write(file.getBytes(),new FileOutputStream(local));
        Attach attach = new Attach();
        attach.setFilename(filename);
        attach.setFilePath(String.join("/","",path,encName));
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

        return R.success(saveFile(SpringUtil.getProperty("upload.local.video"),"video",file));
    }

    @PostMapping("file")
    public Object uploadFile(@RequestParam MultipartFile file){
        return R.success(saveFile(SpringUtil.getProperty("upload.local.file"),"files",file));
    }
}

