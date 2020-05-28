package com.rearwave.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sunyi
 */

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.rearwave.blog"})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
        System.out.println("------------------系统启动成功------------------");
    }
}
