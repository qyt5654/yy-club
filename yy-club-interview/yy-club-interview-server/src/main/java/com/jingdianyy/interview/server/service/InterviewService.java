package com.jingdianyy.interview.server.service;

import com.jingdianyy.interview.api.req.InterviewReq;
import com.jingdianyy.interview.api.req.InterviewSubmitReq;
import com.jingdianyy.interview.api.req.StartReq;
import com.jingdianyy.interview.api.vo.InterviewQuestionVO;
import com.jingdianyy.interview.api.vo.InterviewResultVO;
import com.jingdianyy.interview.api.vo.InterviewVO;

public interface InterviewService {

    InterviewVO analyse(InterviewReq req);

    InterviewQuestionVO start(StartReq req);

    InterviewResultVO submit(InterviewSubmitReq req);
}
