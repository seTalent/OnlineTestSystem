package com.lovejava.mapper;

import com.lovejava.pojo.SingleQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SingleQuestionMapper {
    int deleteByPrimaryKey(Integer qid);

    int insert(SingleQuestion record);

    int insertSelective(SingleQuestion record);

    SingleQuestion selectByPrimaryKey(Integer qid);

    int updateByPrimaryKeySelective(SingleQuestion record);

    int updateByPrimaryKeyWithBLOBs(SingleQuestion record);

    int updateByPrimaryKey(SingleQuestion record);

    @Select("select count(*) from tb_single")
    int getNumber();

    @Select("select qid, question, major, answer_a, answer_b, answer_c, answer_d from tb_single where major=#{major1}")
    List<SingleQuestion>getAllSingle(String major1);


    List<SingleQuestion> selectByMajor(String major);
}