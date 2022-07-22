package com.lovejava.service;

import com.lovejava.pojo.JudgementQuestion;
import com.lovejava.pojo.MultiQuestion;
import com.lovejava.pojo.SaqQuestion;
import com.lovejava.pojo.SingleQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    List<String>getAllMultiA();
    //单选题操作
    List<SingleQuestion>getAllSingle(String major1);//选出所有单选题

    SingleQuestion selectSingleByPrimaryKey(Integer qid);//查找

    int deleteSingleByPrimaryKey(Integer qid);//删除

    int insert(SingleQuestion record);//插入

    int insertSelective(SingleQuestion record);//选择性插入

    int updateByPrimaryKeySelective(SingleQuestion record);//选择性更新

    int updateByPrimaryKey(SingleQuestion record);//更新


    //多选题操作
    List<MultiQuestion> getAllMulti(String major1);

    MultiQuestion selectMultiByPrimaryKey(Integer qid);

    int deleteMultiByPrimaryKey(Integer qid);

    int insert(MultiQuestion record);

    int insertSelective(MultiQuestion record);

    int updateByPrimaryKeySelective(MultiQuestion record);

    int updateByPrimaryKey(MultiQuestion record);

    //判断题操作
    List<JudgementQuestion>getAllJudge(String major1);

    JudgementQuestion selectJudgeByPrimaryKey(Integer qid);

    int deleteJudgementByPrimaryKey(Integer qid);

    int insert(JudgementQuestion record);

    int insertSelective(JudgementQuestion record);

    int updateByPrimaryKeySelective(JudgementQuestion record);

    int updateByPrimaryKey(JudgementQuestion record);

    //简答题操作
    List<SaqQuestion>getAllSaq(String major1);

    SaqQuestion selectSaqByPrimaryKey(Integer qid);

    int deleteSaqByPrimaryKey(Integer qid);

    int insert(SaqQuestion record);

    int insertSelective(SaqQuestion record);

    int updateByPrimaryKeySelective(SaqQuestion record);

    int updateByPrimaryKey(SaqQuestion record);
}