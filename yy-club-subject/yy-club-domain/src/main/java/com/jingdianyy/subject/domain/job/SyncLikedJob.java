package com.jingdianyy.subject.domain.job;

import com.jingdianyy.subject.domain.service.SubjectLikedDomainService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 同步点赞数据
 */
@Component
@Slf4j
public class SyncLikedJob {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 点赞任务
     */
    @XxlJob("syncLikedJobHandler")
    public void syncLikedJobHandler(){
        XxlJobHelper.log("syncLikedJobHandler.start");
        try{
            subjectLikedDomainService.syncLiked();
        }catch (Exception e){
            XxlJobHelper.log("syncLikedJobHandler.error"+e.getMessage());
        }
    }

}