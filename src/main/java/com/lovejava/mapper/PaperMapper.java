package com.lovejava.mapper;

import com.lovejava.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperMapper {
    int deleteByPrimaryKey(String major);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(String major);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);

}