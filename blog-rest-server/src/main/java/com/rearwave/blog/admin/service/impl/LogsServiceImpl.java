package com.rearwave.blog.admin.service.impl;

import com.rearwave.blog.admin.model.Logs;
import com.rearwave.blog.admin.dao.LogsMapper;
import com.rearwave.blog.admin.service.ILogsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author @Moton
 * @since 2020-06-12
 */
@Service
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements ILogsService {

}
