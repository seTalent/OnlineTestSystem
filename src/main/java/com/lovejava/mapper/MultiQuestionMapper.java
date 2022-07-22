package com.lovejava.mapper;

import com.lovejava.pojo.MultiQuestion;
import com.lovejava.pojo.SingleQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MultiQuestionMapper {

    @Select("select count(*) from tb_multiple")
    int getNumber();

    @Select("select qid, question, major, answer_a, answer_b, answer_c, answer_d from tb_multiple where major=#{major1}")
    List<MultiQuestion>getAllMulti(String major1);

    @Select("select answer_a from tb_multiple")
    List<String>getAllMultiA();

    List<MultiQuestion> selectByMajor( String major);

    int deleteByPrimaryKey(Integer qid);

    int insert(MultiQuestion record);

    int insertSelective(MultiQuestion record);

    MultiQuestion selectByPrimaryKey(Integer qid);

    int updateByPrimaryKeySelective(MultiQuestion record);

    int updateByPrimaryKeyWithBLOBs(MultiQuestion record);

    int updateByPrimaryKey(MultiQuestion record);
}