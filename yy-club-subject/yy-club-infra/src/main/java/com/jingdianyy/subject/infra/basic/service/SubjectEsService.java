package com.jingdianyy.subject.infra.basic.service;

import com.jingdianyy.subject.common.entity.PageResult;
import com.jingdianyy.subject.infra.basic.entity.SubjectInfoEs;

public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);

}
