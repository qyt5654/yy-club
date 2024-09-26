package com.jingdianyy.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jingdianyy.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets(){
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }
}