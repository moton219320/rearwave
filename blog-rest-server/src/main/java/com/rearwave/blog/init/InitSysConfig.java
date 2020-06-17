package com.rearwave.blog.init;

import com.rearwave.blog.admin.dao.ConfigMapper;
import com.rearwave.blog.admin.model.Config;
import com.rearwave.blog.component.utils.GSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author sunyi
 */
@Order(1)
@Log4j2
@Component
@DependsOn({"dataSource"})
public class InitSysConfig {


    @Autowired
    private ConfigurableEnvironment environment;
    @Autowired
    private ConfigMapper configMapper;

    @PostConstruct
    public void init() {
        MutablePropertySources propertySources = environment.getPropertySources();
        List<Config> configs = configMapper.selectList(null);
        Map<String,String> conf = configs.stream()
                .collect(Collectors.toMap(Config::getConfigKey,Config::getConfigValue)) ;
        Properties properties = new Properties();
        properties.putAll(conf);
        PropertiesPropertySource sysConf = new PropertiesPropertySource("rearwave.config",properties);
        propertySources.addLast(sysConf);
        log.debug("加载全局配置 - {}", GSON.toJSONString(properties));
    }
}
