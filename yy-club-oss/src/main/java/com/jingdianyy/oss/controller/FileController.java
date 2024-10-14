package com.jingdianyy.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jingdianyy.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

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

    /**
     * 获取文件路径
     * @param bucketName
     * @param objectName
     * @return
     */
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) throws Exception {
        return fileService.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile upload, String bucketName, String objectName) throws Exception {
        objectName = objectName + "/" + upload.getOriginalFilename();
        return fileService.upload(upload, bucketName, objectName);
    }
}
