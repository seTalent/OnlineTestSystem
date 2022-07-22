package com.lovejava.mapper;

import com.lovejava.pojo.SaqQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SaqQuestionMapper {
    int deleteByPrimaryKey(Integer qid);

    int insert(SaqQuestion record);

    int insertSelective(SaqQuestion record);

    SaqQuestion selectByPrimaryKey(Integer qid);

    int updateByPrimaryKeySelective(SaqQuestion record);

    int updateByPrimaryKeyWithBLOBs(SaqQuestion record);

    int updateByPrimaryKey(SaqQuestion record);

    List<SaqQuestion> selectByMajor(String major);

    @Select("select * from tb_saq where major=#{major1}")
    List<SaqQuestion>getAllSaq(String major1);
}