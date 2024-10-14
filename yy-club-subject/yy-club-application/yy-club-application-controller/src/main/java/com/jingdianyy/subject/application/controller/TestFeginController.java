package com.jingdianyy.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfoEs;
import com.jingdianyy.subject.infra.basic.service.SubjectEsService;
import com.jingdianyy.subject.infra.entity.UserInfo;
import com.jingdianyy.subject.infra.rpc.UserRPC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 刷题分类controller
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class TestFeginController {

    @Resource
    private UserRPC userRPC;

    @Resource
    private SubjectEsService subjectEsService;


    @GetMapping("testFeign")
    public void testFeign(){
        UserInfo userInfo = userRPC.getUserInfo("yyppc");
        log.info("testFeign,userInfo:{}",userInfo);
    }

    @PostMapping("/querySubjectByKeyWord")
    public void querySubjectByKeyWord(){
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyWord("mysql");
        PageResult<SubjectInfoEs> pageResult = subjectEsService.querySubjectList(subjectInfoEs);
        log.info("querySubjectByKeyWord:{}", JSON.toJSONString(pageResult));
    }



//    @GetMapping("testCreateIndex")
//    private void testCreateIndex(){
//        subjectEsService.createIndex();
//    }
//
//    @GetMapping("testAddDoc")
//    private void addDoc(){
//        subjectEsService.addDocs();
//    }
//
//    @GetMapping("testFind")
//    private void find(){
//        subjectEsService.find();
//    }

}
