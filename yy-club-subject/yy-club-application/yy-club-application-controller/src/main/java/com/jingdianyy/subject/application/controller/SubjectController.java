package com.jingdianyy.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianyy.subject.application.convert.SubjectAnswerDTOConverter;
import com.jingdianyy.subject.application.convert.SubjectInfoDTOConverter;
import com.jingdianyy.subject.application.dto.SubjectInfoDTO;
import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.common.entity.Result;
import com.jingdianyy.subject.domain.entity.SubjectAnswerBo;
import com.jingdianyy.subject.domain.entity.SubjectInfoBo;
import com.jingdianyy.subject.domain.service.SubjectInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题controller
 */
@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 添加题目
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()),
                    "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()),
                    "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()),
                    "标签id不能为空");

            SubjectInfoBo subjectInfoBo = SubjectInfoDTOConverter.INSTANCE.converterDTOToBo(subjectInfoDTO);
            List<SubjectAnswerBo> subjectAnswerBos =
                    SubjectAnswerDTOConverter.INSTANCE.converterDTOToBo(subjectInfoDTO.getOptionList());
            subjectInfoBo.setOptionList(subjectAnswerBos);
            subjectInfoDomainService.add(subjectInfoBo);
            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}", e.getMessage() ,e);
            return Result.fail("新增题目失败");
        }
    }

    /**
     * 查询题目列表
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }

            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签id不能为空");
            SubjectInfoBo subjectInfoBo = SubjectInfoDTOConverter.INSTANCE.converterDTOToBo(subjectInfoDTO);
            PageResult<SubjectInfoBo> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBo);
            PageResult<SubjectInfoDTO> subjectInfoDTOPageResult = SubjectInfoDTOConverter.INSTANCE.converterBoToPageResultDTO(boPageResult);
            return Result.ok(subjectInfoDTOPageResult);
        }catch (Exception e){
            log.error("SubjectCategoryController.getSubjectPage.error:{}", e.getMessage() ,e);
            return Result.fail("查询题目列表失败");
        }
    }

    /**
     * 查询题目信息
     */
    @PostMapping("/getSubjectInfo")
    public Result<SubjectInfoDTO> getSubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectController.getSubjectInfo.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");
            SubjectInfoBo subjectInfoBo = SubjectInfoDTOConverter.INSTANCE.converterDTOToBo(subjectInfoDTO);
            SubjectInfoBo subjectInfoBO = subjectInfoDomainService.getSubjectInfo(subjectInfoBo);
            SubjectInfoDTO subjectInfoDTO1 = SubjectInfoDTOConverter.INSTANCE.converterBoToDTO(subjectInfoBO);
            return Result.ok(subjectInfoDTO1);
        }catch (Exception e){
            log.error("SubjectCategoryController.getSubjectInfo.error:{}", e.getMessage() ,e);
            return Result.fail("查询题目信息失败");
        }
    }
}
