package com.jingdianyy.practice.server.service;

import com.jingdianyy.practice.api.common.PageResult;
import com.jingdianyy.practice.api.req.GetPracticeSubjectsReq;
import com.jingdianyy.practice.api.req.GetUnCompletePracticeReq;
import com.jingdianyy.practice.api.vo.*;
import com.jingdianyy.practice.server.entity.dto.PracticeSetDTO;
import com.jingdianyy.practice.server.entity.dto.PracticeSubjectDTO;

import java.util.List;

public interface PracticeSetService {
    /**
     * 获取专项练习内容
     * @return
     */
    List<SpecialPracticeVO> getSpecialPracticeContent();

    /**
     * 开始练习
     */
    PracticeSetVO addPractice(PracticeSubjectDTO dto);

    /**
     * 获取练习题
     */
    PracticeSubjectListVO getSubjects(GetPracticeSubjectsReq req);

    /**
     * 获取题目
     */
    PracticeSubjectVO getPracticeSubject(PracticeSubjectDTO dto);

    /**
     * 获取模拟套题内容
     */
    PageResult<PracticeSetVO> getPreSetContent(PracticeSetDTO dto);

    /**
     * 获取未完成练习内容
     */
    PageResult<UnCompletePracticeSetVO> getUnCompletePractice(GetUnCompletePracticeReq req);

}
