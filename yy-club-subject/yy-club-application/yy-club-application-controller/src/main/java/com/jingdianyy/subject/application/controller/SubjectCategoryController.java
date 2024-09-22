package com.jingdianyy.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jingdianyy.subject.application.convert.SubjectCategoryDTOConverter;
import com.jingdianyy.subject.application.dto.SubjectCategoryDTO;
import com.jingdianyy.subject.common.entity.Result;
import com.jingdianyy.subject.domain.entity.SubjectCategoryBo;
import com.jingdianyy.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题分类controller
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 添加分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类父级id不能为空");
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBo);
            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectCategoryController.add.error:{}", e.getMessage() ,e);
            return Result.fail("新增分类失败");
        }
    }

    /**
     * 查询所有分类
     * @return
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            //转换属性
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategory(subjectCategoryDTO);
            List<SubjectCategoryBo> subjectCategoryBoList = subjectCategoryDomainService.queryCategory(subjectCategoryBo);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategoryDTOList(subjectCategoryBoList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage() ,e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 查询父类下的子类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectCategoryController.queryCategoryByPrimary.dto:{}",
                        JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父类id不能为空");
            //转换属性
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategory(subjectCategoryDTO);
            List<SubjectCategoryBo> subjectCategoryBoList = subjectCategoryDomainService.queryCategory(subjectCategoryBo);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategoryDTOList(subjectCategoryBoList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage() ,e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 更新分类
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectCategoryController.update.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategory(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBo);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectCategoryController.update error:{}", e.getMessage() ,e);
            return Result.fail("更新分类失败");
        }
    }

    /**
     * 删除分类
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isDebugEnabled()){
                log.info("SubjectCategoryController.delete.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDTOConverter.INSTANCE
                    .converterBoToCategory(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBo);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectCategoryController.delete error:{}", e.getMessage() ,e);
            return Result.fail("删除分类失败");
        }
    }
}
