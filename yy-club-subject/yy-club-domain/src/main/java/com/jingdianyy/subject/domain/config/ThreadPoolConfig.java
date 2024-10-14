package com.jingdianyy.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池的config管理
 */
@Configuration
public class ThreadPoolConfig {


    @Bean(name = "labelThreadPool")
    public ThreadPoolExecutor getLabelThreadPool(){
        return new ThreadPoolExecutor(20, 100, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(40),
                new CustomNameThreadFactory("label"),//设置名称，可根据报错定位
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
