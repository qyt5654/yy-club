package com.jingdianyy.subject.infra.basic.service.impl;

import com.jingdianyy.subject.infra.basic.entity.SubjectInfo;
import com.jingdianyy.subject.infra.basic.mapper.SubjectInfoDao;
import com.jingdianyy.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-09-22 13:56:00
 */
@Service("subjectInfoService")
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Resource
    private SubjectInfoDao subjectInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectInfo queryById(Long id) {
        return this.subjectInfoDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectInfo subjectInfo) {
        return this.subjectInfoDao.insert(subjectInfo);
    }

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectInfo update(SubjectInfo subjectInfo) {
        this.subjectInfoDao.update(subjectInfo);
        return this.queryById(subjectInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectInfoDao.deleteById(id) > 0;
    }

    /**
     * 查询数量
     * @param subjectInfo
     * @param labelId
     * @param categoryId
     * @return
     */
    @Override
    public int countByCondition(SubjectInfo subjectInfo, Long labelId, Long categoryId) {
        return this.subjectInfoDao.countByCondition(subjectInfo, labelId, categoryId);
    }

    @Override
    public List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long labelId, Long categoryId, int start, Integer pageSize) {
        return this.subjectInfoDao.queryPage(subjectInfo, labelId, categoryId, start, pageSize);
    }

    @Override
    public List<SubjectInfo> getContributeCount() {
        return this.subjectInfoDao.getContributeCount();
    }
}
