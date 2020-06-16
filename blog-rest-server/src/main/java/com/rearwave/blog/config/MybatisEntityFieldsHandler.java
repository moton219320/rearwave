package com.rearwave.blog.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.rearwave.blog.component.utils.WebUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 对象公共属性自动填充实现
 * @author sunyi
 */
@Log4j2
@Component
public class MybatisEntityFieldsHandler  extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("insertFill entity {} fields",metaObject.getOriginalObject().getClass());
        metaObject.setValue("createTime",new Date());
        metaObject.setValue("createUser", WebUtils.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("updateFill entity {} fields",metaObject.getOriginalObject().getClass());
        metaObject.setValue("updateTime",new Date());
        metaObject.setValue("updateUser",WebUtils.getUserId());
    }
}
