package com.jingdianyy.oss.service;

import com.jingdianyy.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.util.List;

@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter){
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket(){
        return storageAdapter.getAllBucket();
    }

    /**
     * 获取文件路径
     * @param bucketName
     * @param objectName
     * @return
     */
    public String getUrl(String bucketName, String objectName) throws Exception {
        return storageAdapter.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     */
    public String upload(MultipartFile upload, String bucketName, String objectName) throws Exception {
        storageAdapter.uploadFile(upload, bucketName, objectName);
        return storageAdapter.getUrl(bucketName, objectName);
    }
}
