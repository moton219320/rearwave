package com.rearwave.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 开启定时器、多线程、feign的支持
 * @author sunyi
 */
@EnableScheduling
@EnableAsync
@EnableCaching
@EnableFeignClients
@MapperScan(basePackages = {"com.rearwave.blog.*.dao"})
@SpringBootApplication(scanBasePackages = {"com.rearwave.blog"})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
        System.err.println("------------------系统启动成功------------------");
    }
}
