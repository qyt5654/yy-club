package com.jingdianyy.practice.server.dao;

import com.jingdianyy.practice.server.entity.dto.CategoryDTO;
import com.jingdianyy.practice.server.entity.po.CategoryPO;
import com.jingdianyy.practice.server.entity.po.PrimaryCategoryPO;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author makejava
 * @since 2024-09-20 10:16:09
 */
public interface SubjectCategoryDao {

    List<PrimaryCategoryPO> getPrimaryCategory(CategoryDTO categoryDTO);

    CategoryPO selectById(@Param("parentId") Long parentId);

    List<CategoryPO> selectList(CategoryDTO categoryDTOTemp);
}

