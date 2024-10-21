package com.jingdianyy.practice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 练习微服务启动类
 *
 * @author: ChickenWing
 * @date: 2024/3/2
 */
@SpringBootApplication
@ComponentScan("com.jingdianyy")
@MapperScan("com.jingdianyy.**.dao")
@EnableFeignClients(basePackages = "com.jingdianyy")
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class);
    }

}
