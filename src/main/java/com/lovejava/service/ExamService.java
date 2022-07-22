package com.lovejava.service;


import com.lovejava.pojo.*;

import java.util.List;

public interface ExamService {


    public List<SingleQuestion> getSingleQuestionByType(Integer number,String major);
    public List<MultiQuestion> getMultiQuestionByType(Integer number, String major);
    public List<JudgementQuestion> getJudgementQuestionByType(Integer number, String major);
    public List<SaqQuestion> getSaqQuestionByType(Integer number, String major);

}
