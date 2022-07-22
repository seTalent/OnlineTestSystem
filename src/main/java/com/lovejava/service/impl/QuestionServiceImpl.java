package com.lovejava.service.impl;

import com.lovejava.mapper.JudgementQuestionMapper;
import com.lovejava.mapper.MultiQuestionMapper;
import com.lovejava.mapper.SaqQuestionMapper;
import com.lovejava.mapper.SingleQuestionMapper;
import com.lovejava.pojo.JudgementQuestion;
import com.lovejava.pojo.MultiQuestion;
import com.lovejava.pojo.SaqQuestion;
import com.lovejava.pojo.SingleQuestion;
import com.lovejava.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private SingleQuestionMapper singleQuestionMapper;
    @Autowired
    private MultiQuestionMapper multiQuestionMapper;
    @Autowired
    private JudgementQuestionMapper judgementQuestionMapper;
    @Autowired
    private SaqQuestionMapper saqQuestionMapper;
    @Override
    public List<String>getAllMultiA(){
        return multiQuestionMapper.getAllMultiA();
    }

    //单选题接口
    @Override
    public int deleteSingleByPrimaryKey(Integer qid) {
        return singleQuestionMapper.deleteByPrimaryKey(qid);
    }

    @Override
    public int insert(SingleQuestion record) {
        return singleQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(SingleQuestion record) {
        return singleQuestionMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SingleQuestion record) {
        return singleQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SingleQuestion record) {
        return singleQuestionMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<SingleQuestion> getAllSingle(String major1) {
        return singleQuestionMapper.getAllSingle(major1);
    }

    @Override
    public SingleQuestion selectSingleByPrimaryKey(Integer qid) {
        return singleQuestionMapper.selectByPrimaryKey(qid);
    }

    //多选题接口
    @Override
    public List<MultiQuestion> getAllMulti(String major1) {
        return multiQuestionMapper.getAllMulti(major1);
    }
    @Override
    public int deleteMultiByPrimaryKey(Integer qid) {
        return multiQuestionMapper.deleteByPrimaryKey(qid);
    }

    @Override
    public int insert(MultiQuestion record) {
        return multiQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(MultiQuestion record) {
        return multiQuestionMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(MultiQuestion record) {
        return multiQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MultiQuestion record) {
        return multiQuestionMapper.updateByPrimaryKey(record);
    }


    @Override
    public MultiQuestion selectMultiByPrimaryKey(Integer qid) {
        return multiQuestionMapper.selectByPrimaryKey(qid);
    }
    //判断题接口
    @Override
    public List<JudgementQuestion> getAllJudge(String major1) {
        return judgementQuestionMapper.getAllJudge(major1);
    }

    @Override
    public int deleteJudgementByPrimaryKey(Integer qid) {
        return judgementQuestionMapper.deleteByPrimaryKey(qid);
    }

    @Override
    public int insert(JudgementQuestion record) {
        return judgementQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(JudgementQuestion record) {
        return judgementQuestionMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(JudgementQuestion record) {
        return judgementQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JudgementQuestion record) {
        return judgementQuestionMapper.updateByPrimaryKey(record);
    }

    @Override
    public JudgementQuestion selectJudgeByPrimaryKey(Integer qid) {
        return judgementQuestionMapper.selectByPrimaryKey(qid);
    }

    //简答题接口
    @Override
    public List<SaqQuestion> getAllSaq(String major1) {
        return saqQuestionMapper.getAllSaq(major1);
    }

    @Override
    public int deleteSaqByPrimaryKey(Integer qid) {
        return saqQuestionMapper.deleteByPrimaryKey(qid);
    }

    @Override
    public int insert(SaqQuestion record) {
        return saqQuestionMapper.insert(record);
    }

    @Override
    public int insertSelective(SaqQuestion record) {
        return saqQuestionMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SaqQuestion record) {
        return saqQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaqQuestion record) {
        return saqQuestionMapper.updateByPrimaryKey(record);
    }

    @Override
    public SaqQuestion selectSaqByPrimaryKey(Integer qid) {
        return saqQuestionMapper.selectByPrimaryKey(qid);
    }
}

