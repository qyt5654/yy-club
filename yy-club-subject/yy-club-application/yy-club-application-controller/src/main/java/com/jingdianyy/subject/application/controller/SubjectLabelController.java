package com.jingdianyy.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianyy.subject.application.convert.SubjectLabelDTOConverter;
import com.jingdianyy.subject.application.dto.SubjectLabelDTO;
import com.jingdianyy.subject.common.entity.Result;
import com.jingdianyy.subject.domain.entity.SubjectLabelBo;
import com.jingdianyy.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签controller
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 添加标签
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()), "标签名称不能为空");
            SubjectLabelBo subjectLabelBo  = SubjectLabelDTOConverter.INSTANCE
                    .converterDtoToLabelBo(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectLabelBo);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.add.error:{}", e.getMessage() ,e);
            return Result.fail("新增标签失败");
        }
    }

    /**
     * 修改标签
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectLabelController.update.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签Id不能为空");
            SubjectLabelBo subjectLabelBo  = SubjectLabelDTOConverter.INSTANCE
                    .converterDtoToLabelBo(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBo);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.update.error:{}", e.getMessage() ,e);
            return Result.fail("修改标签失败");
        }
    }

    /**
     * 删除标签
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签Id不能为空");
            SubjectLabelBo subjectLabelBo  = SubjectLabelDTOConverter.INSTANCE
                    .converterDtoToLabelBo(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBo);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectLabelController.update.error:{}", e.getMessage() ,e);
            return Result.fail("删除标签失败");
        }
    }

    /**
     * 查询分类下标签
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类Id不能为空");
            SubjectLabelBo subjectLabelBo = SubjectLabelDTOConverter.INSTANCE
                    .converterDtoToLabelBo(subjectLabelDTO);
            List<SubjectLabelBo> subjectLabelBoList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBo);
            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelDTOConverter.INSTANCE.converterLabelBoToDtoList(subjectLabelBoList);
            return Result.ok(subjectLabelDTOList);
        }catch (Exception e){
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}", e.getMessage() ,e);
            return Result.fail("查询标签失败");
        }
    }

}
