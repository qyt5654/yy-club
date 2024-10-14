package com.jingdianyy.oss.adapter;

import com.jingdianyy.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储适配器
 */
public interface StorageAdapter {
    /**
     * 创建bucket桶
     */
    void createBucket(String bucket);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 列出所有桶
     */
    List<String> getAllBucket();

    /**
     * 列出当前桶及文件
     */
    List<FileInfo> getAllFile(String bucket);

    /**
     * 下载文件
     * @param bucket
     * @param objectName
     * @return
     */
    InputStream downLoad(String bucket, String objectName);

    /**
     * 删除桶
     */
    void deleteBucket(String bucket);

    /**
     * 删除文件
     */
    void deleteObject(String bucket, String objectName);

    /**
     * 获取文件路径
     * @param bucketName
     * @param objectName
     * @return
     */
    String getUrl(String bucketName, String objectName) throws Exception;
}
