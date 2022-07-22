package com.lovejava.service.impl;

import com.lovejava.mapper.*;
import com.lovejava.pojo.*;
import com.lovejava.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    private final SecureRandom secureRandom=new SecureRandom();

    @Autowired
    private SingleQuestionMapper singleQuestionMapper;

    @Autowired
    private MultiQuestionMapper multiQuestionMapper;

    @Autowired
    private JudgementQuestionMapper judgementQuestionMapper;

    @Autowired
    private SaqQuestionMapper saqQuestionMapper;

    /**
     *
     * @param number 题目数量
     * @param major 题目所属专业
     * @return
     */
    @Override
    public List<SingleQuestion> getSingleQuestionByType(Integer number,String major) {
        int no=0;
        List<SingleQuestion> singleQuestions = singleQuestionMapper.selectByMajor(major);
        int size = singleQuestions.size();
        List<SingleQuestion> questions = new ArrayList<>();
        while(no<number){
            int i = secureRandom.nextInt(size);
            SingleQuestion question = singleQuestions.get(i);
            if(!questions.contains(question)){
                questions.add(question);
                ++no;
            }
        }
        return questions;
    }

    @Override
    public List<MultiQuestion> getMultiQuestionByType(Integer number, String major) {
        int no=0;
        List<MultiQuestion> multiQuestions = multiQuestionMapper.selectByMajor(major);
        int size = multiQuestions.size();
        System.out.println(size+"))))))))))))))))))))))))))))))))))))\n");
        List<MultiQuestion> questions = new ArrayList<>();
        while(no<number){
            int i = secureRandom.nextInt(size);
            MultiQuestion question = multiQuestions.get(i);
            if(!questions.contains(question)){
                questions.add(question);
                ++no;
            }
        }

        return questions;
    }

    @Override
    public List<JudgementQuestion> getJudgementQuestionByType(Integer number,String major) {
        int no=0;
        List<JudgementQuestion> judgementQuestions = judgementQuestionMapper.selectByMajor(major);
        int size = judgementQuestions.size();
        List<JudgementQuestion> questions = new ArrayList<>();
        while(no<number){
            int i = secureRandom.nextInt(size);
            JudgementQuestion question = judgementQuestions.get(i);
            if(!questions.contains(question)){
                questions.add(question);
                ++no;
            }
        }

        return questions;
    }

    @Override
    public List<SaqQuestion> getSaqQuestionByType(Integer number, String major ) {
        int no=0;
        List<SaqQuestion> saQuestions = saqQuestionMapper.selectByMajor(major);
        int size = saQuestions.size();
        List<SaqQuestion> questions = new ArrayList<>();
        while(no<number){
            int i = secureRandom.nextInt(size);
            SaqQuestion question = saQuestions.get(i);
            if(!questions.contains(question)){
                questions.add(question);
                ++no;
            }
        }

        return questions;
    }
}
