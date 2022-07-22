package com.lovejava.mapper;

import com.lovejava.pojo.JudgementQuestion;
import com.lovejava.pojo.SingleQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface JudgementQuestionMapper {

    @Select("select count(*) from tb_judgement")
    int getNumber();

    List<JudgementQuestion> selectByMajor(String major);
    int deleteByPrimaryKey(Integer qid);

    int insert(JudgementQuestion record);

    int insertSelective(JudgementQuestion record);

    JudgementQuestion selectByPrimaryKey(Integer qid);

    int updateByPrimaryKeySelective(JudgementQuestion record);

    int updateByPrimaryKeyWithBLOBs(JudgementQuestion record);

    int updateByPrimaryKey(JudgementQuestion record);

    @Select("select * from tb_judgement where major=#{major1}")
    List<JudgementQuestion>getAllJudge(String major1);
}